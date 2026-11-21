
package servicio;

import acceso_datos.NotificacionDAO;
import modelo.Notificacion;
import java.util.ArrayList;
import java.sql.Connection;

public class NotificacionServicio {
    private NotificacionDAO notificacionDAO;
    
    public NotificacionServicio(){
        this.notificacionDAO = new NotificacionDAO();
    }
    
    public boolean registrar(Notificacion notificacion, Connection conexion){

        if(notificacion == null){
            return false;
        }

        if(notificacion.getIdUsuario() <= 0){
            return false;
        }

        if(notificacion.getMensaje() == null || notificacion.getMensaje().trim().isEmpty()){
            return false;
        }

        if(notificacion.getIdListaEspera() != null && notificacion.getIdListaEspera() <= 0){
            return false;
        }

        return notificacionDAO.registrar(notificacion, conexion);
    }
    
    public boolean registrar(Notificacion notificacion){
        if(notificacion == null){
            return false;
        }
        
        if(notificacion.getIdUsuario() <= 0){
            return false;
        }
        
        if(notificacion.getMensaje() == null || notificacion.getMensaje().trim().isEmpty()){
            return false;
        }
        
        if(notificacion.getIdListaEspera() != null && notificacion.getIdListaEspera() <= 0){
            return false;
        }
        
        return notificacionDAO.registrar(notificacion);
    }
    
    public ArrayList<Notificacion> listarPorUsuario(long idUsuario){
        if(idUsuario <= 0){
            return null;
        }
        
        return notificacionDAO.listarPorUsuario(idUsuario);
    }
    
    public ArrayList<Notificacion> listarNoLeidas(long idUsuario){
        if(idUsuario <= 0){
            return null;
        }
        
        return notificacionDAO.listarNoLeidas(idUsuario);
    }
    
    public boolean marcarComoLeida(long idNotificacion){
        if(idNotificacion <= 0){
            return false;
        }
        
        return notificacionDAO.marcarComoLeida(idNotificacion);
    }
    
    public boolean marcarTodasComoLeidas(long idUsuario){
        if(idUsuario <= 0){
            return false;
        }
        
        return notificacionDAO.marcarTodasComoLeidas(idUsuario);
    }
}
