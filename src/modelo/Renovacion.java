
package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Renovacion {
    private long idRenovacion;
    private long idPrestamo;
    private LocalDate fechaLimiteAnterior;
    private LocalDate nuevaFechaLimite;
    private LocalDateTime fechaRenovacion;

    public Renovacion(long idPrestamo, LocalDate fechaLimiteAnterior, LocalDate nuevaFechaLimite) {
        this.idPrestamo = idPrestamo;
        this.fechaLimiteAnterior = fechaLimiteAnterior;
        this.nuevaFechaLimite = nuevaFechaLimite;
    }

    public long getIdRenovacion() {
        return idRenovacion;
    }

    public void setIdRenovacion(long idRenovacion) {
        this.idRenovacion = idRenovacion;
    }

    public long getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public LocalDate getFechaLimiteAnterior() {
        return fechaLimiteAnterior;
    }

    public void setFechaLimiteAnterior(LocalDate fechaLimiteAnterior) {
        this.fechaLimiteAnterior = fechaLimiteAnterior;
    }

    public LocalDate getNuevaFechaLimite() {
        return nuevaFechaLimite;
    }

    public void setNuevaFechaLimite(LocalDate nuevaFechaLimite) {
        this.nuevaFechaLimite = nuevaFechaLimite;
    }

    public LocalDateTime getFechaRenovacion() {
        return fechaRenovacion;
    }

    public void setFechaRenovacion(LocalDateTime fechaRenovacion) {
        this.fechaRenovacion = fechaRenovacion;
    }
    
}
