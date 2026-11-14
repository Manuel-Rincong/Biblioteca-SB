
package modelo;

import java.time.LocalDateTime;

public class ListaEspera {
    private long idListaEspera;
    private long idUsuario;
    private long idRecurso;
    private LocalDateTime fechaInscripcion;
    private String estado;
    private LocalDateTime fechaAviso;
    private LocalDateTime fechaVencimientoAviso;

    public ListaEspera(long idUsuario, long idRecurso) {
        this.idUsuario = idUsuario;
        this.idRecurso = idRecurso;
    }

    public long getIdListaEspera() {
        return idListaEspera;
    }

    public void setIdListaEspera(long idListaEspera) {
        this.idListaEspera = idListaEspera;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(long idRecurso) {
        this.idRecurso = idRecurso;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaAviso() {
        return fechaAviso;
    }

    public void setFechaAviso(LocalDateTime fechaAviso) {
        this.fechaAviso = fechaAviso;
    }

    public LocalDateTime getFechaVencimientoAviso() {
        return fechaVencimientoAviso;
    }

    public void setFechaVencimientoAviso(LocalDateTime fechaVencimientoAviso) {
        this.fechaVencimientoAviso = fechaVencimientoAviso;
    }

}
