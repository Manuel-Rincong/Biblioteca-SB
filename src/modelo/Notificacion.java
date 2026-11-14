
package modelo;

import java.time.LocalDateTime;


public class Notificacion {
    private long idNotificacion;
    private long idUsuario;
    private String mensaje;
    private Long idListaEspera;
    private LocalDateTime fechaCreacion;
    private boolean leida = false;

    public Notificacion(long idUsuario, String mensaje, Long idListaEspera) {
        this.idUsuario = idUsuario;
        this.mensaje = mensaje;
        this.idListaEspera = idListaEspera;
    }

    public long getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getIdListaEspera() {
        return idListaEspera;
    }

    public void setIdListaEspera(Long idListaEspera) {
        this.idListaEspera = idListaEspera;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }
    
}
