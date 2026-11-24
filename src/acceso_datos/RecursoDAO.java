
package acceso_datos;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Recurso;

public class RecursoDAO {
    
    private Recurso convertirRecurso(ResultSet resultado) throws SQLException {
        long id = resultado.getLong("id_recurso");
        String identificador = resultado.getString("identificador");
        String nombre = resultado.getString("nombre");
        long idTipoRecurso = resultado.getLong("id_tipo_recurso");
        String descripcion = resultado.getString("descripcion");
        boolean disponible = resultado.getBoolean("disponible");
        boolean activo = resultado.getBoolean("activo");

        Recurso recurso = new Recurso(identificador, nombre, idTipoRecurso, descripcion);
        recurso.setIdRecurso(id);
        recurso.setDisponible(disponible);
        recurso.setActivo(activo);

        return recurso;
    }
    
    public boolean actualizarDisponibilidad(long idRecurso, boolean disponible, Connection conexion){
        
        String sql = "UPDATE recurso SET disponible = ? WHERE id_recurso = ?";
        
        try(PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setBoolean(1, disponible);
            sentencia.setLong(2, idRecurso);

            return sentencia.executeUpdate() > 0;

        }catch(SQLException e){
            System.out.println("Error al actualizar la disponibilidad del recurso: " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarDisponibilidad(long idRecurso, boolean disponible){

        String sql = "UPDATE recurso SET disponible = ? WHERE id_recurso = ?";

        Connection conexion = ConexionBD.getInstancia().obtenerConexion();

        if(conexion == null){
            return false;
        }

        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setBoolean(1, disponible);
            sentencia.setLong(2, idRecurso);

            return sentencia.executeUpdate() > 0;

        }catch(SQLException e){
            System.out.println("Error al actualizar la disponibilidad del recurso: " + e.getMessage());
            return false;
        }
    }
    
    public boolean desactivar(long idRecurso){
        
        String sql = "UPDATE recurso SET activo = FALSE WHERE id_recurso = ?";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idRecurso);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al desactivar el recurso: "+e.getMessage());
            return false;
        }
    }
    
    public boolean actualizar(Recurso recurso){
        
        String sql = "UPDATE recurso SET identificador = ?, nombre = ?, id_tipo_recurso = ?, descripcion = ?, disponible = ?, activo = ? WHERE id_recurso = ?";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, recurso.getIdentificador());
            sentencia.setString(2, recurso.getNombre());
            sentencia.setLong(3, recurso.getIdTipoRecurso());
            sentencia.setString(4, recurso.getDescripcion());
            sentencia.setBoolean(5, recurso.isDisponible());
            sentencia.setBoolean(6, recurso.isActivo());
            sentencia.setLong(7, recurso.getIdRecurso());
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al actualizar el recurso: "+e.getMessage());
            return false;
        }
        
    }
    
    public ArrayList<Recurso> listarTodos(){
        
        String sql = "SELECT * FROM recurso";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<Recurso> recursos = new ArrayList<>();
            
            while(resultado.next()){
                Recurso recurso = convertirRecurso(resultado);
                recursos.add(recurso);
            }
            
            return recursos;
            
        }catch(SQLException e){
            System.out.println("Error al listar todos los recursos: "+e.getMessage());
            return null;
        }
    }
    
    public Recurso buscarPorIdConBloqueo(long idRecurso, Connection conexion){
        
        String sql = "SELECT * FROM recurso WHERE id_recurso = ? FOR UPDATE";
        
        try(PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idRecurso);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
               return convertirRecurso(resultado); 
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al buscar los recursos por el id: "+e.getMessage());
            return null;
        }
    }
    
    public Recurso buscarPorId(long idRecurso){
        
        String sql = "SELECT * FROM recurso WHERE id_recurso = ?";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idRecurso);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
               return convertirRecurso(resultado); 
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al buscar los recursos por el id: "+e.getMessage());
            return null;
        }
    }
    
    public boolean registrar(Recurso recurso){
        
        String sql = "INSERT INTO recurso (identificador, nombre, id_tipo_recurso, descripcion) VALUES (?, ?, ?, ?)";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, recurso.getIdentificador());
            sentencia.setString(2, recurso.getNombre());
            sentencia.setLong(3, recurso.getIdTipoRecurso());
            sentencia.setString(4, recurso.getDescripcion());
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al registrar los datos de recurso: "+e.getMessage());
            return false;
        }
    }
}
