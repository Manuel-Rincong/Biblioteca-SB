
package acceso_datos;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.ReglaPrestamo;

public class ReglaPrestamoDAO {
    
    private ReglaPrestamo convertirReglaPrestamo(ResultSet resultado) throws SQLException {
        long id = resultado.getLong("id_regla");
        long idTipoUsuario = resultado.getLong("id_tipo_usuario");
        long idTipoRecurso = resultado.getLong("id_tipo_recurso");
        int plazoDias = resultado.getInt("plazo_dias");
        int limitePrestamos = resultado.getInt("limite_prestamos");
        int maxRenovaciones = resultado.getInt("max_renovaciones");
        boolean activa = resultado.getBoolean("activa");

        ReglaPrestamo regla = new ReglaPrestamo(idTipoUsuario, idTipoRecurso, plazoDias, limitePrestamos, maxRenovaciones);

        regla.setIdRegla(id);
        regla.setActiva(activa);

        return regla;
    }
    
    public ReglaPrestamo buscarPorTipoUsuarioYRecurso(long idTipoUsuario, long idTipoRecurso, Connection conexion){

        String sql = "SELECT * FROM regla_prestamo WHERE id_tipo_usuario = ? AND id_tipo_recurso = ? AND activa = TRUE";

        try(PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setLong(1, idTipoUsuario);
            sentencia.setLong(2, idTipoRecurso);

            try(ResultSet resultado = sentencia.executeQuery()){

                if(resultado.next()){
                    return convertirReglaPrestamo(resultado);
                }

                return null;
            }

        }catch(SQLException e){
            System.out.println("Error al buscar la regla de préstamo: " + e.getMessage());
            return null;
        }
    }
    
    public ReglaPrestamo buscarPorTipoUsuarioYRecurso(long idTipoUsuario, long idTipoRecurso){
        
        String sql = "SELECT * FROM regla_prestamo WHERE id_tipo_usuario = ? AND id_tipo_recurso = ? AND activa = TRUE";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idTipoUsuario);
            sentencia.setLong(2, idTipoRecurso);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                return convertirReglaPrestamo(resultado);
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al buscar la regla de prestamo por el tipo de usuario y recurso: "+e.getMessage());
            return null;
        }
    }
    
    public boolean desactivar(long idRegla){
        
        String sql = "UPDATE regla_prestamo SET activa = FALSE WHERE id_regla = ?";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idRegla);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al desactivar la regla de prestamo: "+e.getMessage());
            return false;
        }
    }
    
    public boolean actualizar(ReglaPrestamo reglaPrestamo){
        
        String sql = "UPDATE regla_prestamo SET id_tipo_usuario = ?, id_tipo_recurso = ?, plazo_dias = ?, limite_prestamos = ?,  max_renovaciones = ?, activa = ? WHERE id_regla = ?";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
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
            System.out.println("Error al actualizar la regla de prestamo: "+e.getMessage());
            return false;
        }
    }
    
    public ArrayList<ReglaPrestamo> listarTodos(){
        
        String sql = "SELECT * FROM regla_prestamo";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<ReglaPrestamo> reglas = new ArrayList<>();
            
            
            while(resultado.next()){
                ReglaPrestamo regla = convertirReglaPrestamo(resultado);
                reglas.add(regla);
            }
            
            return reglas;
            
        }catch(SQLException e){
            System.out.println("Error al listar todas las reglas de prestamos: "+e.getMessage());
            return null;
        }
    }
    
    public ReglaPrestamo buscarPorId(long idRegla){
        
        String sql = "SELECT * FROM regla_prestamo WHERE id_regla = ?";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idRegla);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                return convertirReglaPrestamo(resultado);
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al buscar la regla de prestamo por el id: "+e.getMessage());
            return null;
        }
    }
    
    public boolean registrar(ReglaPrestamo reglaPrestamo){
        
        String sql = "INSERT INTO regla_prestamo (id_tipo_usuario, id_tipo_recurso, plazo_dias, limite_prestamos, max_renovaciones) VALUES (?, ?, ?, ?, ?)";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
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
            System.out.println("Error al registar los datos de regla de prestamo: "+e.getMessage());
            return false;
        }
    }
   
}
