
package acceso_datos;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.ReglaPrestamo;

public class ReglaPrestamoDAO {
    
    public ReglaPrestamo buscarPorTipoUsuarioYRecurso(long idTipoUsuario, long idTipoRecurso){
        
        String sql = "SELECT * FROM regla_prestamo WHERE id_tipo_usuario = ? AND id_tipo_recurso = ? AND activa = TRUE";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idTipoUsuario);
            sentencia.setLong(2, idTipoRecurso);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                long id = resultado.getLong("id_regla");
                long idTUsuario = resultado.getLong("id_tipo_usuario");
                long idTRecurso = resultado.getLong("id_tipo_recurso");
                int plazoDias = resultado.getInt("plazo_dias");
                int limitePrestamos = resultado.getInt("limite_prestamos");
                int maxRenovaciones = resultado.getInt("max_renovaciones");
                boolean activa = resultado.getBoolean("activa");
                
                ReglaPrestamo consulta = new ReglaPrestamo(idTUsuario, idTRecurso, plazoDias, limitePrestamos, maxRenovaciones);
                consulta.setIdRegla(id);
                consulta.setActiva(activa);
                
                return consulta;
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al consultar la regla de prestamo: "+e.getMessage());
            return null;
        }
    }
    
    public boolean eliminar(long idRegla){
        
        String sql = "DELETE FROM regla_prestamo WHERE id_regla = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idRegla);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al intentar eliminar la regla de prestamo: "+e.getMessage());
            return false;
        }
    }
    
    public boolean actualizar(ReglaPrestamo reglaPrestamo){
        
        String sql = "UPDATE regla_prestamo SET id_tipo_usuario = ?, id_tipo_recurso = ?, plazo_dias = ?, limite_prestamos = ?,  max_renovaciones = ?, activa = ? WHERE id_regla = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, reglaPrestamo.getIdTipoUsuario());
            sentencia.setLong(2, reglaPrestamo.getIdTipoRecurso());
            sentencia.setInt(3, reglaPrestamo.getPlazoDias());
            sentencia.setInt(4, reglaPrestamo.getLimitePrestamos());
            sentencia.setInt(5, reglaPrestamo.getMaxRenovaciones());
            sentencia.setBoolean(6, reglaPrestamo.isActiva());
            sentencia.setLong(7, reglaPrestamo.getIdRegla());
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al intentar actualizar las reglas de prestamo: "+e.getMessage());
            return false;
        }
    }
    
    public ArrayList<ReglaPrestamo> listarTodos(){
        
        String sql = "SELECT * FROM regla_prestamo";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<ReglaPrestamo> reglas = new ArrayList<>();
            
            
            while(resultado.next()){
                long id = resultado.getLong("id_regla");
                long idTipoUsuario = resultado.getLong("id_tipo_usuario");
                long idTipoRecurso = resultado.getLong("id_tipo_recurso");
                int plazoDias = resultado.getInt("plazo_dias");
                int limitePrestamos = resultado.getInt("limite_prestamos");
                int maxRenovaciones = resultado.getInt("max_renovaciones");
                boolean activa = resultado.getBoolean("activa");
                
                ReglaPrestamo consulta = new ReglaPrestamo(idTipoUsuario, idTipoRecurso, plazoDias, limitePrestamos, maxRenovaciones);
                consulta.setIdRegla(id);
                consulta.setActiva(activa);
                
                reglas.add(consulta);
                
            }
            
            return reglas;
            
        }catch(SQLException e){
            System.out.println("Error al intentar consultar las reglas de prestamos: "+e.getMessage());
            return null;
        }
    }
    
    public ReglaPrestamo buscarPorId(long idRegla){
        
        String sql = "SELECT * FROM regla_prestamo WHERE id_regla = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idRegla);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                long id = resultado.getLong("id_regla");
                long idTipoUsuario = resultado.getLong("id_tipo_usuario");
                long idTipoRecurso = resultado.getLong("id_tipo_recurso");
                int plazoDias = resultado.getInt("plazo_dias");
                int limitePrestamos = resultado.getInt("limite_prestamos");
                int maxRenovaciones = resultado.getInt("max_renovaciones");
                boolean activa = resultado.getBoolean("activa");
                
                ReglaPrestamo consulta = new ReglaPrestamo(idTipoUsuario, idTipoRecurso, plazoDias, limitePrestamos, maxRenovaciones);
                consulta.setIdRegla(id);
                consulta.setActiva(activa);
                
                return consulta;
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al intentar buscar por el id de regla: "+e.getMessage());
            return null;
        }
    }
    
    public boolean registrar(ReglaPrestamo reglaPrestamo){
        
        String sql = "INSERT INTO regla_prestamo (id_tipo_usuario, id_tipo_recurso, plazo_dias, limite_prestamos, max_renovaciones) VALUES (?, ?, ?, ?, ?)";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, reglaPrestamo.getIdTipoUsuario());
            sentencia.setLong(2, reglaPrestamo.getIdTipoRecurso());
            sentencia.setInt(3, reglaPrestamo.getPlazoDias());
            sentencia.setInt(4, reglaPrestamo.getLimitePrestamos());
            sentencia.setInt(5, reglaPrestamo.getMaxRenovaciones());
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al intentar registar los datos de regla de prestamo: "+e.getMessage());
            return false;
        }
    }
   
}
