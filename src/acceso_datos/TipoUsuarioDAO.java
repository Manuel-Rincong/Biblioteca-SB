
package acceso_datos;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.TipoUsuario;

public class TipoUsuarioDAO {
    
    private TipoUsuario convertirTipoUsuario(ResultSet resultado) throws SQLException{
        long id = resultado.getLong("id_tipo_usuario");
        String nombre = resultado.getString("nombre");
        int limite = resultado.getInt("limite_prestamos");
        boolean activo = resultado.getBoolean("activo");
                
        TipoUsuario consulta = new TipoUsuario(nombre, limite);
        consulta.setIdTipoUsuario(id);
        consulta.setActivo(activo);
                
        return consulta;
    }
    
    public boolean desactivar(long idTipoUsuario){
        
        String sql = "UPDATE tipo_usuario SET activo = FALSE WHERE id_tipo_usuario = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idTipoUsuario);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al desactivar el tipo de usuario: "+e.getMessage());
            return false;
        }
    }
    
    public boolean actualizar(TipoUsuario tipoUsuario){
        
        String sql = "UPDATE tipo_usuario SET nombre = ?, limite_prestamos = ?, activo = ? WHERE id_tipo_usuario = ? ";
        
        Connection conexion  = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, tipoUsuario.getNombre());
            sentencia.setInt(2, tipoUsuario.getLimitePrestamos());
            sentencia.setBoolean(3, tipoUsuario.isActivo());
            sentencia.setLong(4, tipoUsuario.getIdTipoUsuario());
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al actualizar el tipo de usuario: "+e.getMessage());
            return false;
        }
        
    }
    
    public boolean registrar(TipoUsuario tipoUsuario){
        
        String sql = "INSERT INTO tipo_usuario (nombre, limite_prestamos) VALUES (?, ?)";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try (conexion;
             PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, tipoUsuario.getNombre());
            sentencia.setInt(2, tipoUsuario.getLimitePrestamos());
            
            return sentencia.executeUpdate() > 0;
            
        } catch (SQLException e){
            System.out.println("Error al registrar el tipo de usuario: "+ e.getMessage());
            return false;
        }
             
    }
    
    public TipoUsuario buscarPorId(long idTipoUsuario){
        String sql = "SELECT * FROM tipo_usuario WHERE id_tipo_usuario = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return null;
        }
        
        try(conexion; 
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idTipoUsuario);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if (resultado.next()){          
                return convertirTipoUsuario(resultado);
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al buscar por el id del tipo de usuario: "+ e.getMessage());
            return null;
        }
    }
    
    public ArrayList<TipoUsuario> listarTodos(){
        
        String sql = "SELECT * FROM tipo_usuario";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
                PreparedStatement sentencia  = conexion.prepareStatement (sql)){
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<TipoUsuario> tipoUsuarios = new ArrayList<>();
            
            while(resultado.next()){
                TipoUsuario tipoUsuario = convertirTipoUsuario(resultado);
                tipoUsuarios.add(tipoUsuario);
            }
            
            return tipoUsuarios;
            
        }catch(SQLException e){
            System.out.println("Error al listar todos los tipos de usuarios: "+e.getMessage());
            return null;
        }
    }  
}
