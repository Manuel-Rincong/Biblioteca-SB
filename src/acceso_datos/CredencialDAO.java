
package acceso_datos;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Credencial;

public class CredencialDAO {
    
    private Credencial convertirCredencial(ResultSet resultado) throws SQLException {
        long id = resultado.getLong("id_credencial");
        long idUsuario = resultado.getLong("id_usuario");
        String contrasenaHash = resultado.getString("contrasena_hash");
        boolean activo = resultado.getBoolean("activo");

        Credencial credencial = new Credencial(idUsuario, contrasenaHash, activo);
        credencial.setIdCredencial(id);

        return credencial;
    }
    
    public boolean desactivar(long idUsuario){
        
        String sql = "UPDATE credencial SET activo = FALSE WHERE id_usuario = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idUsuario);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al desactivar la credencial: "+e.getMessage());
            return false;
        }
    }
    
    public boolean actualizarContrasena(long idUsuario, String contrasenaHash){
        
        String sql = "UPDATE credencial SET contrasena_hash = ? WHERE id_usuario = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, contrasenaHash);
            sentencia.setLong(2, idUsuario);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al actualizar contraseña en credencial: "+e.getMessage());
            return false;
        }
    }
    
    public Credencial buscarPorUsuario(long idUsuario){
        
        String sql = "SELECT * FROM credencial WHERE id_usuario = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia  = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idUsuario);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                return convertirCredencial(resultado);
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al buscar credencial por el usuario: "+e.getMessage());
            return null;
        }
    }
    
    public boolean registrar(Credencial credencial){
        
        if(credencial == null){
            return false;
        }
        
        String sql = "INSERT INTO credencial (id_usuario, contrasena_hash, activo) VALUES (?, ?, ?)";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, credencial.getIdUsuario());
            sentencia.setString(2, credencial.getContrasenaHash());
            sentencia.setBoolean(3, credencial.isActivo());
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al registrar las credenciales: "+e.getMessage());
            return false;
        }
    }
}
