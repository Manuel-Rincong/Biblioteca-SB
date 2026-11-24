
package acceso_datos;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.TipoRecurso;

public class TipoRecursoDAO {
    
    private TipoRecurso convertirTipoRecurso(ResultSet resultado) throws SQLException {
        long id = resultado.getLong("id_tipo_recurso");
        String nombre = resultado.getString("nombre");
        String descripcion = resultado.getString("descripcion");
        boolean activo = resultado.getBoolean("activo");

        TipoRecurso tipoRecurso = new TipoRecurso(nombre, descripcion);
        tipoRecurso.setIdTipoRecurso(id);
        tipoRecurso.setActivo(activo);

        return tipoRecurso;
    }
    
    public boolean desactivar(long idTipoRecurso){
        
        String sql = "UPDATE tipo_recurso SET activo = FALSE WHERE id_tipo_recurso = ?";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idTipoRecurso);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al desactivar el tipo de recurso: "+e.getMessage());
            return false;
        }
    }
    
    public boolean actualizar(TipoRecurso tipoRecurso){
        
        String sql = "UPDATE tipo_recurso SET nombre = ?, descripcion = ?, activo = ? WHERE id_tipo_recurso = ?";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, tipoRecurso.getNombre());
            sentencia.setString(2, tipoRecurso.getDescripcion());
            sentencia.setBoolean(3, tipoRecurso.isActivo());
            sentencia.setLong(4, tipoRecurso.getIdTipoRecurso());
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al actualizar el tipo de recurso: "+e.getMessage());
            return false;
        }
    }
    
    public ArrayList<TipoRecurso> listarTodos(){
        
        String sql = "SELECT * FROM tipo_recurso";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if (conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<TipoRecurso> tipos = new ArrayList<>();
            
            while(resultado.next()){
                TipoRecurso tipoRecurso = convertirTipoRecurso(resultado);
                tipos.add(tipoRecurso);
            }
            
            return tipos;
            
        }catch(SQLException e){
            System.out.println("Error al listar todos los tipos de recursos: "+e.getMessage());
            return null;
        }
    }
    
    public TipoRecurso buscarPorId(long idTipoRecurso){
        
        String sql = "SELECT * FROM tipo_recurso WHERE id_tipo_recurso = ?";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if (conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idTipoRecurso);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                return convertirTipoRecurso(resultado);
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al buscar el tipo de recurso por el id:  "+ e.getMessage());
            return null;
        }
    }
    
    public boolean registrar(TipoRecurso tipoRecurso){
        
        String sql = "INSERT INTO tipo_recurso (nombre, descripcion) VALUES (?, ?)";
        
        Connection conexion = ConexionBD.getInstancia().obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, tipoRecurso.getNombre());
            sentencia.setString(2, tipoRecurso.getDescripcion());
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al registrar el tipo de recurso: "+e.getMessage());
            return false;
        }
    }
}
