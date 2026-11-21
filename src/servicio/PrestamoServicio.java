
package servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import acceso_datos.PrestamoDAO;
import acceso_datos.RecursoDAO;
import acceso_datos.UsuarioDAO;
import acceso_datos.ReglaPrestamoDAO;
import acceso_datos.ListaEsperaDAO;
import acceso_datos.RenovacionDAO;
import acceso_datos.NotificacionDAO;

import modelo.Prestamo;
import modelo.Recurso;
import modelo.Usuario;
import modelo.ReglaPrestamo;
import modelo.ListaEspera;
import modelo.Renovacion;
import modelo.Notificacion;

import java.sql.Connection;
import conexion.ConexionBD;
import java.sql.SQLException;

public class PrestamoServicio {
    private PrestamoDAO prestamoDAO;
    private RecursoDAO recursoDAO;
    private UsuarioDAO usuarioDAO;
    private ReglaPrestamoDAO reglaPrestamoDAO;
    private ListaEsperaDAO listaEsperaDAO;
    private RenovacionDAO renovacionDAO;
    private NotificacionDAO notificacionDAO;
    
    public PrestamoServicio(){
        this.prestamoDAO = new PrestamoDAO();
        this.recursoDAO = new RecursoDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.reglaPrestamoDAO = new ReglaPrestamoDAO();
        this.listaEsperaDAO = new ListaEsperaDAO();
        this.renovacionDAO = new RenovacionDAO();
        this.notificacionDAO = new NotificacionDAO();
    }
    
    public int contarActivosPorUsuario(long idUsuario){
        if(idUsuario <= 0){
            return (-1);
        }
        
        return prestamoDAO.contarActivosPorUsuario(idUsuario);
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
     
    public boolean cancelar(long idPrestamo){
        if(idPrestamo <= 0){
            return false;
        }
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion){
            
            conexion.setAutoCommit(false);
            
            try{
                
                Prestamo prestamoInicial = prestamoDAO.buscarPorId(idPrestamo, conexion);

                if(prestamoInicial == null){
                    conexion.rollback();
                    return false;
                }
                
                Usuario usuario = usuarioDAO.buscarPorIdConBloqueo(prestamoInicial.getIdUsuario(), conexion);

                if(usuario == null || !usuario.isActivo()){
                    conexion.rollback();
                    return false;
                }
                
                Recurso recurso = recursoDAO.buscarPorIdConBloqueo(prestamoInicial.getIdRecurso(), conexion);
                
                if(recurso == null || !recurso.isActivo()){
                    conexion.rollback();
                    return false;
                }
                
                Prestamo prestamoBloqueado = prestamoDAO.buscarPorIdConBloqueo(idPrestamo, conexion);

                if(prestamoBloqueado == null || !"ACTIVO".equals(prestamoBloqueado.getEstado())){
                    conexion.rollback();
                    return false;
                }
                
                if(prestamoBloqueado.getIdUsuario() != usuario.getIdUsuario() || prestamoBloqueado.getIdRecurso() != recurso.getIdRecurso()){
                    conexion.rollback();
                    return false;
                }
                
                boolean prestamoActualizado = prestamoDAO.actualizarEstado(idPrestamo, "CANCELADO", conexion);

                if(!prestamoActualizado){
                    conexion.rollback();
                    return false;
                }
                
                ListaEspera siguienteEnEspera = listaEsperaDAO.buscarSiguienteEnEspera(recurso.getIdRecurso(), conexion);
                
                if(siguienteEnEspera != null){

                    boolean listaActualizada = listaEsperaDAO.notificarSiguiente(
                        siguienteEnEspera.getIdListaEspera(), conexion);

                    if(!listaActualizada){
                        conexion.rollback();
                        return false;
                    }

                    Notificacion notificacion = new Notificacion(
                        siguienteEnEspera.getIdUsuario(),
                        "El recurso que solicitaste está disponible. Tienes 2 días para solicitarlo.",
                        siguienteEnEspera.getIdListaEspera()
                    );

                    boolean notificacionRegistrada = notificacionDAO.registrar(
                        notificacion, conexion);

                    if(!notificacionRegistrada){
                        conexion.rollback();
                        return false;
                    }

                }else{

                    boolean recursoActualizado = recursoDAO.actualizarDisponibilidad(
                        recurso.getIdRecurso(), true, conexion);

                    if(!recursoActualizado){
                        conexion.rollback();
                        return false;
                    }
                }
                conexion.commit();
                return true; 
                
            }catch(SQLException e){
                conexion.rollback();
                throw e; 
                
            }
            
        }catch(SQLException e){
            System.out.println("Error al cancelar el prestamo: " + e.getMessage());
            return false;
            
        }
    }
    
    public boolean devolver(long idPrestamo){
        if(idPrestamo <= 0){
            return false;
        }
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion){
            
            conexion.setAutoCommit(false);
            
            try{
                
                Prestamo prestamoInicial = prestamoDAO.buscarPorId(idPrestamo, conexion);

                if(prestamoInicial == null){
                    conexion.rollback();
                    return false;
                }
                
                Usuario usuario = usuarioDAO.buscarPorIdConBloqueo(prestamoInicial.getIdUsuario(), conexion);

                if(usuario == null || !usuario.isActivo()){
                    conexion.rollback();
                    return false;
                }
                
                Recurso recurso = recursoDAO.buscarPorIdConBloqueo(prestamoInicial.getIdRecurso(), conexion);
                
                if(recurso == null || !recurso.isActivo()){
                    conexion.rollback();
                    return false;
                }
                
                Prestamo prestamoBloqueado = prestamoDAO.buscarPorIdConBloqueo(idPrestamo, conexion);

                if(prestamoBloqueado == null || !"ACTIVO".equals(prestamoBloqueado.getEstado())){
                    conexion.rollback();
                    return false;
                }
                
                if(prestamoBloqueado.getIdUsuario() != usuario.getIdUsuario() || prestamoBloqueado.getIdRecurso() != recurso.getIdRecurso()){
                    conexion.rollback();
                    return false;
                }
                
                LocalDate fechaDevolucion = LocalDate.now();

                boolean prestamoActualizado = prestamoDAO.actualizarDevolucion(idPrestamo, fechaDevolucion, conexion);

                if(!prestamoActualizado){
                    conexion.rollback();
                    return false;
                }
                
                ListaEspera siguienteEnEspera = listaEsperaDAO.buscarSiguienteEnEspera(recurso.getIdRecurso(), conexion);
                
                if(siguienteEnEspera != null){

                    boolean listaActualizada = listaEsperaDAO.notificarSiguiente(siguienteEnEspera.getIdListaEspera(), conexion);

                    if(!listaActualizada){
                        conexion.rollback();
                        return false;
                    }

                    Notificacion notificacion = new Notificacion(siguienteEnEspera.getIdUsuario(),
                        "El recurso que solicitaste está disponible. Tienes 2 días para solicitarlo.",
                        siguienteEnEspera.getIdListaEspera()
                    );

                    boolean notificacionRegistrada = notificacionDAO.registrar(
                        notificacion, conexion);

                    if(!notificacionRegistrada){
                        conexion.rollback();
                        return false;
                    }
                }
                
                else{
                    boolean recursoActualizado = recursoDAO.actualizarDisponibilidad(
                        recurso.getIdRecurso(), true, conexion);

                    if(!recursoActualizado){
                        conexion.rollback();
                        return false;
                    }
                }
                
                conexion.commit();
                return true;    
                
            }catch(SQLException e){
                conexion.rollback();
                throw e; 
            }
            
        }catch(SQLException e){
            System.out.println("Error al devolver el prestamo: " + e.getMessage());
            return false;
            
        }
    }
    
    public boolean renovar(long idPrestamo){
        if(idPrestamo <= 0){
            return false;
        }
        
        Connection conexion = ConexionBD.obtenerConexion();

        if(conexion == null){
            return false;
        }
        
        try(conexion){
            
            conexion.setAutoCommit(false);
            
            try{
                
                Prestamo prestamoInicial = prestamoDAO.buscarPorId(idPrestamo, conexion);

                if(prestamoInicial == null){
                    conexion.rollback();
                    return false;
                }
                
                Usuario usuario = usuarioDAO.buscarPorIdConBloqueo(prestamoInicial.getIdUsuario(), conexion);

                if(usuario == null || !usuario.isActivo()){
                    conexion.rollback();
                    return false;
                }
                
                Recurso recurso = recursoDAO.buscarPorIdConBloqueo(prestamoInicial.getIdRecurso(), conexion);
                
                if(recurso == null || !recurso.isActivo()){
                    conexion.rollback();
                    return false;
                }
                
                ReglaPrestamo regla = reglaPrestamoDAO.buscarPorTipoUsuarioYRecurso(usuario.getIdTipoUsuario(), recurso.getIdTipoRecurso(), conexion);

                if(regla == null || !regla.isActiva()){
                    conexion.rollback();
                    return false;
                }
                
                Prestamo prestamoBloqueado = prestamoDAO.buscarPorIdConBloqueo(idPrestamo, conexion);

                if(prestamoBloqueado == null || !"ACTIVO".equals(prestamoBloqueado.getEstado())){
                    conexion.rollback();
                    return false;
                }
                
                if(prestamoBloqueado.getIdUsuario() != usuario.getIdUsuario() || prestamoBloqueado.getIdRecurso() != recurso.getIdRecurso()){
                    conexion.rollback();
                    return false;
                }

                if(prestamoBloqueado.getFechaLimite().isBefore(LocalDate.now())){
                    conexion.rollback();
                    return false;
                }
                
                if(prestamoBloqueado.getRenovacionesRealizadas() >= regla.getMaxRenovaciones()){
                    conexion.rollback();
                    return false;
                }
                
                ListaEspera siguienteEnEspera = listaEsperaDAO.buscarSiguienteEnEspera(prestamoBloqueado.getIdRecurso(), conexion);

                if(siguienteEnEspera != null){
                    conexion.rollback();
                    return false;
                }
                
                LocalDate fechaLimiteAnterior = prestamoBloqueado.getFechaLimite();

                LocalDate nuevaFechaLimite = fechaLimiteAnterior.plusDays(regla.getPlazoDias());
                
                int nuevasRenovaciones = prestamoBloqueado.getRenovacionesRealizadas() + 1;

                boolean prestamoActualizado = prestamoDAO.actualizarRenovacion(idPrestamo, nuevaFechaLimite, nuevasRenovaciones, conexion);

                if(!prestamoActualizado){
                    conexion.rollback();
                    return false;
                }
                
                Renovacion renovacion = new Renovacion(idPrestamo, fechaLimiteAnterior, nuevaFechaLimite);
                
                boolean renovacionRegistrada = renovacionDAO.registrar(renovacion, conexion);

                if(!renovacionRegistrada){
                    conexion.rollback();
                    return false;
                }
                
                conexion.commit();
                return true;
                
            }catch(SQLException e){
                conexion.rollback();
                throw e;
            }

        }catch(SQLException e){
            System.out.println("Error al renovar el prestamo: " + e.getMessage());
            return false;
        }
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
            System.out.println("Error al registrar el prestamo: " + e.getMessage());
            return false;
        }
    }
}
