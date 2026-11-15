
package acceso_datos;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Usuario;


public class UsuarioDAO {
    
    public boolean eliminar(long idUsuario){
        
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idUsuario);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al intentar eliminar el usuario"+e.getMessage());
            return false;
        }
    }
    
    public boolean actualizar(Usuario usuario){
        
        String sql = "UPDATE usuario SET identificacion = ?, nombre = ?, correo = ?, id_tipo_usuario = ?, activo = ? WHERE id_usuario = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, usuario.getIdentificacion());
            sentencia.setString(2, usuario.getNombre());
            sentencia.setString(3, usuario.getCorreo());
            sentencia.setLong(4, usuario.getIdTipoUsuario());
            sentencia.setBoolean(5, usuario.isActivo());
            sentencia.setLong(6, usuario.getIdUsuario());
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al intentar actualizar el usuario: "+e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Usuario> listarTodos(){
        
        String sql = "SELECT * FROM usuario";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<Usuario> usuarios = new ArrayList<>();
            
            while(resultado.next()){
                long id = resultado.getLong("id_usuario");
                String identificacion = resultado.getString("identificacion");
                String nombre = resultado.getString("nombre");
                String correo = resultado.getString("correo");
                long idTipoUsuario = resultado.getLong("id_tipo_usuario");
                boolean activo = resultado.getBoolean("activo");
                
                Usuario consulta = new Usuario(identificacion, nombre, correo, idTipoUsuario);
                consulta.setIdUsuario(id);
                consulta.setActivo(activo);
                
                usuarios.add(consulta);
            }
            
            return usuarios;
            
        }catch(SQLException e){
            System.out.println("Error al consultar los usuarios: "+e.getMessage());
            return null;
        }
    }
    
    public Usuario buscarPorId(long idUsuario){
        
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia  = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idUsuario);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                long id = resultado.getLong("id_usuario");
                String identificacion = resultado.getString("identificacion");
                String nombre = resultado.getString("nombre");
                String correo = resultado.getString("correo");
                long idTipoUsuario = resultado.getLong("id_tipo_usuario");
                boolean activo = resultado.getBoolean("activo");
                
                Usuario consulta = new Usuario(identificacion, nombre, correo, idTipoUsuario);
                consulta.setIdUsuario(id);
                consulta.setActivo(activo);
                
                return consulta;
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al consultar el id del usuario: "+e.getMessage());
            return null;
        }
    }
    
    public boolean registrar(Usuario usuario){
        
        String sql = "INSERT INTO usuario (identificacion, nombre, correo, id_tipo_usuario) VALUES (?, ?, ?, ?)";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, usuario.getIdentificacion());
            sentencia.setString(2, usuario.getNombre());
            sentencia.setString(3, usuario.getCorreo());
            sentencia.setLong(4, usuario.getIdTipoUsuario());
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al registrar el usuario: "+e.getMessage());
            return false;
        }
            
    }
       
}
