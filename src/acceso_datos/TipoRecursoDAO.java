
package acceso_datos;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.TipoRecurso;

public class TipoRecursoDAO {
    
    public boolean eliminar(long idTipoRecurso){
        
        String sql = "DELETE FROM tipo_recurso WHERE id_tipo_recurso = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idTipoRecurso);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al intentar eliminar el tipo de recurso: "+e.getMessage());
            return false;
        }
    }
    
    public boolean actualizar(TipoRecurso tipoRecurso){
        
        String sql = "UPDATE tipo_recurso SET nombre = ?, descripcion = ? WHERE id_tipo_recurso = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, tipoRecurso.getNombre());
            sentencia.setString(2, tipoRecurso.getDescripcion());
            sentencia.setLong(3, tipoRecurso.getIdTipoRecurso());
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al intentar actualizar el tipo de recurso: "+e.getMessage());
            return false;
        }
    }
    
    public ArrayList<TipoRecurso> listarTodos(){
        
        String sql = "SELECT * FROM tipo_recurso";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<TipoRecurso> tipos = new ArrayList<>();
            
            while(resultado.next()){
                long id = resultado.getLong("id_tipo_recurso");
                String nombre = resultado.getString("nombre");
                String descripcion = resultado.getString("descripcion");
                
                TipoRecurso consulta = new TipoRecurso(nombre, descripcion);
                consulta.setIdTipoRecurso(id);
                
                tipos.add(consulta);
            }
            
            return tipos;
            
        }catch(SQLException e){
            System.out.println("Error al intentar consultar los tipos de recursos: "+e.getMessage());
            return null;
        }
    }
    
    public TipoRecurso buscarPorId(long idTipoRecurso){
        
        String sql = "SELECT * FROM tipo_recurso WHERE id_tipo_recurso = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idTipoRecurso);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                long id = resultado.getLong("id_tipo_recurso");
                String nombre = resultado.getString("nombre");
                String descripcion = resultado.getString("descripcion");
                
                TipoRecurso consulta = new TipoRecurso(nombre, descripcion);
                consulta.setIdTipoRecurso(id);
                
                return consulta;
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al intentar consultar el id de tipo de recurso:  "+ e.getMessage());
            return null;
        }
    }
    
    public boolean registrar(TipoRecurso tipoRecurso){
        
        String sql = "INSERT INTO tipo_recurso (nombre, descripcion) VALUES (?, ?)";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, tipoRecurso.getNombre());
            sentencia.setString(2, tipoRecurso.getDescripcion());
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al intentar registrar el tipo de recurso: "+e.getMessage());
            return false;
        }
    }
}
