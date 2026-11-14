
package modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Multa {
    private long idMulta;
    private long idPrestamo;
    private long idPolitica;
    private int diasRetraso;
    private BigDecimal montoTotal;
    private String estado;
    private LocalDateTime fechaGeneracion;

    public Multa(long idPrestamo, long idPolitica, int diasRetraso, BigDecimal montoTotal, String estado) {
        this.idPrestamo = idPrestamo;
        this.idPolitica = idPolitica;
        this.diasRetraso = diasRetraso;
        this.montoTotal = montoTotal;
        this.estado = estado;
    }

    public long getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(long idMulta) {
        this.idMulta = idMulta;
    }

    public long getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public long getIdPolitica() {
        return idPolitica;
    }

    public void setIdPolitica(long idPolitica) {
        this.idPolitica = idPolitica;
    }

    public int getDiasRetraso() {
        return diasRetraso;
    }

    public void setDiasRetraso(int diasRetraso) {
        this.diasRetraso = diasRetraso;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }
       
}
