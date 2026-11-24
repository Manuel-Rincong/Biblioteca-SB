
package acceso_datos;

import conexion.ConexionBD;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Prestamo;

public class PrestamoDAO {
    
    private Prestamo convertirPrestamo(ResultSet resultado) throws SQLException{
        
        long id = resultado.getLong("id_prestamo");
        long idUsuario = resultado.getLong("id_usuario");
        long idRecurso = resultado.getLong("id_recurso");

        LocalDate fechaPrestamo = resultado.getDate("fecha_prestamo").toLocalDate();
        LocalDate fechaLimite = resultado.getDate("fecha_limite").toLocalDate();
        
        LocalDate fechaDevolucion = null;

        java.sql.Date fechaSQL = resultado.getDate("fecha_devolucion");

        if (fechaSQL != null) {
            fechaDevolucion = fechaSQL.toLocalDate();
        }
        
        int renovacionesRealizadas = resultado.getInt("renovaciones_realizadas");
        String estado = resultado.getString("estado");
        
        Prestamo consulta = new Prestamo(idUsuario, idRecurso, fechaPrestamo, fechaLimite, fechaDevolucion, estado);
        
        consulta.setIdPrestamo(id);
        consulta.setRenovacionesRealizadas(renovacionesRealizadas);
        
        return consulta;
    }
    
    public boolean actualizarRenovacion(long idPrestamo, LocalDate nuevaFechaLimite, int renovacionesRealizadas, Connection conexion){
        
        String sql = "UPDATE prestamo SET fecha_limite = ?, renovaciones_realizadas = ? WHERE id_prestamo = ? AND estado = 'ACTIVO'";
        
        try(PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setDate(1, java.sql.Date.valueOf(nuevaFechaLimite));
            sentencia.setInt(2, renovacionesRealizadas);
            sentencia.setLong(3, idPrestamo);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al actualizar la renovacion del prestamo: "+e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarDevolucion(long idPrestamo, LocalDate fechaDevolucion, Connection conexion){
        
        String sql = "UPDATE prestamo SET fecha_devolucion = ?, estado = 'DEVUELTO' WHERE id_prestamo = ?";
        
        try(PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setDate(1, java.sql.Date.valueOf(fechaDevolucion));
            sentencia.setLong(2, idPrestamo);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al actualizar la devolucion del prestamo: "+e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarDevolucion(long idPrestamo, LocalDate fechaDevolucion){
        
        String sql = "UPDATE prestamo SET fecha_devolucion = ?, estado = 'DEVUELTO' WHERE id_prestamo = ?";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setDate(1, java.sql.Date.valueOf(fechaDevolucion));
            sentencia.setLong(2, idPrestamo);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al actualizar la devolucion del prestamo: "+e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarEstado(long idPrestamo, String estado, Connection conexion){
        
        String sql = "UPDATE prestamo SET estado = ? WHERE id_prestamo = ?";
         
        try(PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, estado);
            sentencia.setLong(2, idPrestamo);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al actualizar el estado de prestamo: "+e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarEstado(long idPrestamo, String estado){
        
        String sql = "UPDATE prestamo SET estado = ? WHERE id_prestamo = ?";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, estado);
            sentencia.setLong(2, idPrestamo);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al actualizar el estado de prestamo: "+e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Prestamo> listarVencidos(){
        
        String sql = "SELECT * FROM prestamo WHERE estado = 'ACTIVO' AND fecha_limite < CURRENT_DATE";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<Prestamo> prestamos = new ArrayList<>();
            
            while(resultado.next()){
                Prestamo prestamo = convertirPrestamo(resultado);
                prestamos.add(prestamo);
            }
            
            return prestamos;
            
        }catch(SQLException e){
            System.out.println("Error al listar los prestamos vencidos: "+ e.getMessage());
            return null;
        }
    }
    
    public int contarActivosPorUsuario(long idUsuario, Connection conexion){

        String sql = "SELECT COUNT(*) FROM prestamo WHERE id_usuario = ? AND estado = 'ACTIVO'";

        try(PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setLong(1, idUsuario);

            try(ResultSet resultado = sentencia.executeQuery()){

                if(resultado.next()){
                    return resultado.getInt("count");
                }

                return 0;
            }

        }catch(SQLException e){
            System.out.println("Error al contar activos por usuario: " + e.getMessage());
            return -1;
        }
    }
    
    public int contarActivosPorUsuario(long idUsuario){
        
        String sql = "SELECT COUNT(*) FROM prestamo WHERE id_usuario = ? AND estado = 'ACTIVO'";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if(conexion == null){
            return (-1);
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idUsuario);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                return resultado.getInt("count");
            }
            
            return 0;
            
        }catch(SQLException e){
            System.out.println("Error al contar activos por usuario: "+e.getMessage());
            return (-1);
        }
    }
    
    public Prestamo buscarActivoPorRecurso(long idRecurso){
        
        String sql = "SELECT * FROM prestamo WHERE id_recurso = ? AND estado = 'ACTIVO' LIMIT 1";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if (conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idRecurso);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                return convertirPrestamo(resultado);
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al buscar prestamo activo por recurso: "+e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Prestamo> listarActivosPorUsuario(long idUsuario){
        
        String sql = "SELECT * FROM prestamo WHERE id_usuario = ? AND estado = 'ACTIVO'";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if (conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idUsuario);
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<Prestamo> prestamos = new ArrayList<>();
            
            while(resultado.next()){
                Prestamo prestamo = convertirPrestamo(resultado);
                prestamos.add(prestamo);
            }
            
            return prestamos;
            
        }catch(SQLException e){
            System.out.println("Error al listar los prestamos activos por usuario: "+ e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Prestamo> listarPorUsuario(long idUsuario){
        
        String sql = "SELECT * FROM prestamo WHERE id_usuario = ?";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if (conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idUsuario);
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<Prestamo> prestamos = new ArrayList<>();
            
            while(resultado.next()){
                Prestamo prestamo = convertirPrestamo(resultado);
                prestamos.add(prestamo);
            }
            
            return prestamos;
            
        }catch(SQLException e){
            System.out.println("Error al listar los prestamos por el id del usuario: "+ e.getMessage());
            return null;
        }
        
    }
    
    public ArrayList<Prestamo> listarTodos(){
        
        String sql = "SELECT * FROM prestamo";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if (conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<Prestamo> prestamos = new ArrayList<>();
            
            while(resultado.next()){
                Prestamo prestamo = convertirPrestamo(resultado);
                prestamos.add(prestamo);  
            }
            
            return prestamos;
            
        }catch(SQLException e){
            System.out.println("Error al intentar listar todos los prestamos: "+ e.getMessage());
            return null;
        }
    }
    
    public Prestamo buscarPorIdConBloqueo(long idPrestamo, Connection conexion){
        
        String sql = "SELECT * FROM prestamo WHERE id_prestamo = ? FOR UPDATE";
        
        try(PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idPrestamo);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                return convertirPrestamo(resultado);
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al buscar por el id de prestamo: "+e.getMessage());
            return null;
        }
    }
    
    public Prestamo buscarPorId(long idPrestamo, Connection conexion){

        String sql = "SELECT * FROM prestamo WHERE id_prestamo = ?";

        try(PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setLong(1, idPrestamo);

            try(ResultSet resultado = sentencia.executeQuery()){

                if(resultado.next()){
                    return convertirPrestamo(resultado);
                }

                return null;
            }

        }catch(SQLException e){
            System.out.println("Error al buscar por el id de prestamo: " + e.getMessage());
            return null;
        }
    }
    
    public Prestamo buscarPorId(long idPrestamo){
        
        String sql = "SELECT * FROM prestamo WHERE id_prestamo = ?";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if (conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idPrestamo);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                return convertirPrestamo(resultado);
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al buscar por el id de prestamo: "+e.getMessage());
            return null;
        }
    }
    
    public boolean registrar(Prestamo prestamo, Connection conexion){

        String sql = "INSERT INTO prestamo (id_usuario, id_recurso, fecha_prestamo, fecha_limite, fecha_devolucion, renovaciones_realizadas, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setLong(1, prestamo.getIdUsuario());
            sentencia.setLong(2, prestamo.getIdRecurso());
            sentencia.setDate(3, java.sql.Date.valueOf(prestamo.getFechaPrestamo()));
            sentencia.setDate(4, java.sql.Date.valueOf(prestamo.getFechaLimite()));

            if(prestamo.getFechaDevolucion() != null){
                sentencia.setDate(5, java.sql.Date.valueOf(prestamo.getFechaDevolucion()));
            }else{
                sentencia.setNull(5, java.sql.Types.DATE);
            }

            sentencia.setInt(6, prestamo.getRenovacionesRealizadas());
            sentencia.setString(7, prestamo.getEstado());

            return sentencia.executeUpdate() > 0;

        }catch(SQLException e){
            System.out.println("Error al registrar el préstamo: " + e.getMessage());
            return false;
        }
    }
    
    public boolean registrar(Prestamo prestamo){
        
        String sql = "INSERT INTO prestamo (id_usuario, id_recurso, fecha_prestamo, fecha_limite, fecha_devolucion, renovaciones_realizadas, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, prestamo.getIdUsuario());
            sentencia.setLong(2, prestamo.getIdRecurso());
            sentencia.setDate(3, java.sql.Date.valueOf(prestamo.getFechaPrestamo()));
            sentencia.setDate(4, java.sql.Date.valueOf(prestamo.getFechaLimite()));
            
            if (prestamo.getFechaDevolucion() != null) {
                sentencia.setDate(5, java.sql.Date.valueOf(prestamo.getFechaDevolucion()));
            } else {
                sentencia.setNull(5, java.sql.Types.DATE);
            }
            
            sentencia.setInt(6, prestamo.getRenovacionesRealizadas());
            sentencia.setString(7, prestamo.getEstado());
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al registrar los datos en prestamo: "+e.getMessage());
            return false;
        }
    }
    
}
