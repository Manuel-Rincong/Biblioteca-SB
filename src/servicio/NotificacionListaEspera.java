
package servicio;

import modelo.Notificacion;
import java.sql.Connection;

public class NotificacionListaEspera implements DisponibilidadObserver{
    
    private NotificacionServicio notificacionServicio;
    
    public NotificacionListaEspera(){
        this.notificacionServicio = new NotificacionServicio();
    }
    
    @Override
    public boolean actualizar(long idRecurso, long idUsuario, long idListaEspera, Connection conexion){

        if(idRecurso <= 0 || idUsuario <= 0 || idListaEspera <= 0 || conexion == null){
            return false;
        }

        Notificacion notificacion = new Notificacion(
            idUsuario,
            "El recurso que solicitaste está disponible. Tienes 2 días para solicitarlo.",
            idListaEspera
        );

        return notificacionServicio.registrar(notificacion, conexion);
    }
    
}
