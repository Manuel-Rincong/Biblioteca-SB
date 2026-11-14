
package modelo;

import java.time.LocalDate;

public class Prestamo {
    private long idPrestamo;
    private long idUsuario;
    private long idRecurso;
    private LocalDate fechaPrestamo;
    private LocalDate fechaLimite;
    private LocalDate fechaDevolucion;
    private int renovacionesRealizadas = 0;
    private String estado;

    public Prestamo(long idUsuario, long idRecurso, LocalDate fechaPrestamo, LocalDate fechaLimite, String estado) {
        this.idUsuario = idUsuario;
        this.idRecurso = idRecurso;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
    }

    public Prestamo(long idUsuario, long idRecurso, LocalDate fechaPrestamo, LocalDate fechaLimite, LocalDate fechaDevolucion, String estado) {
        this.idUsuario = idUsuario;
        this.idRecurso = idRecurso;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaLimite = fechaLimite;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
    }

    public long getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(long idPrestamo) {
        this.idPrestamo = idPrestamo;
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

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getRenovacionesRealizadas() {
        return renovacionesRealizadas;
    }

    public void setRenovacionesRealizadas(int renovacionesRealizadas) {
        this.renovacionesRealizadas = renovacionesRealizadas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
