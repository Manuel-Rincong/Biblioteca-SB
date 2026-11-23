
package modelo;

import java.math.BigDecimal;

public class PoliticaMulta {
    private long idPolitica;
    private String nombre;
    private BigDecimal valorPorDia;
    private int diasGracia = 0;
    private BigDecimal multaMaxima;
    private boolean activa = true;
    private String tipoEstrategia = "POR_DIA";
    private long idTipoRecurso;

    public PoliticaMulta(String nombre, BigDecimal valorPorDia) {
        this.nombre = nombre;
        this.valorPorDia = valorPorDia;
    }

    public PoliticaMulta(String nombre, BigDecimal valorPorDia, BigDecimal multaMaxima) {
        this.nombre = nombre;
        this.valorPorDia = valorPorDia;
        this.multaMaxima = multaMaxima;
    }

    public long getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(long idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }
    
    public String getTipoEstrategia() {
        return tipoEstrategia;
    }

    public void setTipoEstrategia(String tipoEstrategia) {
        this.tipoEstrategia = tipoEstrategia;
    }
    
    

    public long getIdPolitica() {
        return idPolitica;
    }

    public void setIdPolitica(long idPolitica) {
        this.idPolitica = idPolitica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getValorPorDia() {
        return valorPorDia;
    }

    public void setValorPorDia(BigDecimal valorPorDia) {
        this.valorPorDia = valorPorDia;
    }

    public int getDiasGracia() {
        return diasGracia;
    }

    public void setDiasGracia(int diasGracia) {
        this.diasGracia = diasGracia;
    }

    public BigDecimal getMultaMaxima() {
        return multaMaxima;
    }

    public void setMultaMaxima(BigDecimal multaMaxima) {
        this.multaMaxima = multaMaxima;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
}
