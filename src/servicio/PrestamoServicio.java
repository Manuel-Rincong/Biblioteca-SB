
package servicio;

import java.util.ArrayList;
import acceso_datos.PrestamoDAO;
import acceso_datos.RecursoDAO;
import acceso_datos.UsuarioDAO;
import acceso_datos.ReglaPrestamoDAO;
import modelo.Prestamo;
import modelo.Recurso;
import modelo.Usuario;
import modelo.ReglaPrestamo;
import java.sql.Connection;
import conexion.ConexionBD;
import java.sql.SQLException;

public class PrestamoServicio {
    private PrestamoDAO prestamoDAO;
    private RecursoDAO recursoDAO;
    private UsuarioDAO usuarioDAO;
    private ReglaPrestamoDAO reglaPrestamoDAO;
    
    public PrestamoServicio(){
        this.prestamoDAO = new PrestamoDAO();
        this.recursoDAO = new RecursoDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.reglaPrestamoDAO = new ReglaPrestamoDAO();
    }
    
    public ArrayList<Prestamo> listarVencidos(){
        return prestamoDAO.listarVencidos();
    }
    
    public ArrayList<Prestamo> listarActivosPorUsuario(long idUsuario){
        if(idUsuario <= 0){
            return null;
        }
        
        return prestamoDAO.listarActivosPorUsuario(idUsuario);
    }
    
    public ArrayList<Prestamo> listarPorUsuario(long idUsuario){
        if(idUsuario <= 0){
            return null;
        }
        
        return prestamoDAO.listarPorUsuario(idUsuario);
    }
    
    public ArrayList<Prestamo> listarTodos(){
        return prestamoDAO.listarTodos();
    }
    
    public Prestamo buscarPorId(long idPrestamo){
        if(idPrestamo <= 0){
            return null;
        }
        
        return prestamoDAO.buscarPorId(idPrestamo);
    }
    
    public boolean registrar(Prestamo prestamo){

        if(prestamo == null){
            return false;
        }

        if(prestamo.getIdUsuario() <= 0 || prestamo.getIdRecurso() <= 0){
            return false;
        }

        if(prestamo.getFechaPrestamo() == null || prestamo.getFechaLimite() == null){
            return false;
        }

        if(!prestamo.getFechaLimite().isAfter(prestamo.getFechaPrestamo())){
            return false;
        }

        Connection conexion = ConexionBD.obtenerConexion();

        if(conexion == null){
            return false;
        }

        try(conexion){

            conexion.setAutoCommit(false);

            try {
                
                Usuario usuarioBloqueado = usuarioDAO.buscarPorIdConBloqueo(prestamo.getIdUsuario(), conexion);

                if(usuarioBloqueado == null || !usuarioBloqueado.isActivo()){
                    conexion.rollback();
                    return false;
                }

                // 1. Bloquear y consultar el recurso
                Recurso recurso = recursoDAO.buscarPorIdConBloqueo(prestamo.getIdRecurso(), conexion);

                if(recurso == null || !recurso.isActivo() || !recurso.isDisponible()){
                    conexion.rollback();
                    return false;
                }

                // 2. Buscar la regla de préstamo
                ReglaPrestamo regla = reglaPrestamoDAO.buscarPorTipoUsuarioYRecurso(usuarioBloqueado.getIdTipoUsuario(), recurso.getIdTipoRecurso(), conexion);

                if(regla == null || !regla.isActiva()){
                    conexion.rollback();
                    return false;
                }

                // 3. Comprobar el límite de préstamos
                int prestamosActivos = prestamoDAO.contarActivosPorUsuario(usuarioBloqueado.getIdUsuario(), conexion);

                if(prestamosActivos < 0 || prestamosActivos >= regla.getLimitePrestamos()){
                    conexion.rollback();
                    return false;
                }

                // 4. Comprobar el plazo permitido
                long diasPrestamo = java.time.temporal.ChronoUnit.DAYS.between(prestamo.getFechaPrestamo(),prestamo.getFechaLimite());

                if(diasPrestamo > regla.getPlazoDias()){
                    conexion.rollback();
                    return false;
                }

                // 5. Registrar el préstamo
                
                prestamo.setEstado("ACTIVO");
                prestamo.setRenovacionesRealizadas(0);
                prestamo.setFechaDevolucion(null);
                
                boolean prestamoRegistrado = prestamoDAO.registrar(prestamo, conexion);

                if(!prestamoRegistrado){
                    conexion.rollback();
                    return false;
                }

                // 6. Actualizar disponibilidad
                boolean recursoActualizado = recursoDAO.actualizarDisponibilidad(prestamo.getIdRecurso(), false, conexion);

                if(!recursoActualizado){
                    conexion.rollback();
                    return false;
                }

                // 7. Confirmar transacción
                conexion.commit();
                return true;

            }catch(SQLException e){
                conexion.rollback();
                throw e;
            }

        }catch(SQLException e){
            System.out.println("Error al registrar el préstamo: " + e.getMessage());
            return false;
        }
    }
}
