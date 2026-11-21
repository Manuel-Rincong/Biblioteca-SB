
package acceso_datos;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelo.Notificacion;

public class NotificacionDAO {
    
    private Notificacion convertirNotificacion(ResultSet resultado) throws SQLException{
        long id = resultado.getLong("id_notificacion");
        long idUsuario = resultado.getLong("id_usuario");
        String mensaje = resultado.getString("mensaje");
        
        long idListaEsperaSQL = resultado.getLong("id_lista_espera");
        Long idListaEspera = resultado.wasNull() ? null : idListaEsperaSQL;
        
        LocalDateTime fechaCreacion = resultado.getTimestamp("fecha_creacion").toLocalDateTime();
        boolean leida = resultado.getBoolean("leida");
        
        Notificacion consulta = new Notificacion(idUsuario, mensaje, idListaEspera);
        consulta.setIdNotificacion(id);
        consulta.setFechaCreacion(fechaCreacion);
        consulta.setLeida(leida);
        
        return consulta;
    }
    
    public boolean marcarTodasComoLeidas(long idUsuario){
        
        String sql = "UPDATE notificacion SET leida = TRUE WHERE id_usuario = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idUsuario);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al marcar todas las notificaciones como leidas: "+e.getMessage());
            return false;
        }
    }
    
    public boolean marcarComoLeida(long idNotificacion){
        
        String sql = "UPDATE notificacion SET leida = TRUE WHERE id_notificacion = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idNotificacion);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al marcar la notificacion como leida: "+e.getMessage());
            return false;
        }
    }
    
    public  ArrayList<Notificacion> listarNoLeidas(long idUsuario){
        
        String sql = "SELECT * FROM notificacion WHERE id_usuario = ? AND leida = FALSE";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idUsuario);
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<Notificacion> notificaciones = new ArrayList<>();
            
            while(resultado.next()){
                Notificacion notificacion = convertirNotificacion(resultado);
                notificaciones.add(notificacion);
            }
            
            return notificaciones;
            
        }catch(SQLException e){
            System.out.println("Error al listar las notificaciones por no leidas: "+e.getMessage());
            return null;
        }
    
    }
    
    public ArrayList<Notificacion> listarPorUsuario(long idUsuario){
        
        String sql = "SELECT * FROM notificacion WHERE id_usuario = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idUsuario);
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<Notificacion> notificaciones = new ArrayList<>();
            
            while(resultado.next()){
                Notificacion notificacion = convertirNotificacion(resultado);
                notificaciones.add(notificacion);
            }
            
            return notificaciones;
            
        }catch(SQLException e){
            System.out.println("Error al listar las notificaciones por el id del usuario: "+e.getMessage());
            return null;
        }
    }
    
    public boolean registrar(Notificacion notificacion, Connection conexion){

        if(notificacion == null){
            return false;
        }

        String sql = "INSERT INTO notificacion (id_usuario, id_lista_espera, mensaje) VALUES (?, ?, ?)";

        try(PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setLong(1, notificacion.getIdUsuario());

            if(notificacion.getIdListaEspera() == null){
                sentencia.setNull(2, java.sql.Types.BIGINT);
            }else{
                sentencia.setLong(2, notificacion.getIdListaEspera());
            }

            sentencia.setString(3, notificacion.getMensaje());

            return sentencia.executeUpdate() > 0;

        }catch(SQLException e){
            System.out.println("Error al registrar la notificacion: " + e.getMessage());
            return false;
        }
    }
    
    public boolean registrar(Notificacion notificacion){
        
        String sql = "INSERT INTO notificacion (id_usuario, mensaje, id_lista_espera) VALUES(?, ?, ?)";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, notificacion.getIdUsuario());
            sentencia.setString(2, notificacion.getMensaje());
            
            if (notificacion.getIdListaEspera() != null) {
                sentencia.setLong(3, notificacion.getIdListaEspera());
            } else {
                sentencia.setNull(3, java.sql.Types.BIGINT);
            }
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al registrar los datos en notificacion: "+e.getMessage());
            return false;
        }
    }
}
