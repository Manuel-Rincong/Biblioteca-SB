
package modelo;

public class ReglaPrestamo {
    private long idRegla;
    private long idTipoUsuario;
    private long idTipoRecurso;
    private int plazoDias;
    private int limitePrestamos;
    private int maxRenovaciones;
    private boolean activa = true;

    public ReglaPrestamo(long idTipoUsuario, long idTipoRecurso, int plazoDias, int limitePrestamos, int maxRenovaciones) {
        this.idTipoUsuario = idTipoUsuario;
        this.idTipoRecurso = idTipoRecurso;
        this.plazoDias = plazoDias;
        this.limitePrestamos = limitePrestamos;
        this.maxRenovaciones = maxRenovaciones;
    }

    public long getIdRegla() {
        return idRegla;
    }

    public void setIdRegla(long idRegla) {
        this.idRegla = idRegla;
    }

    public long getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(long idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public long getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(long idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    public int getPlazoDias() {
        return plazoDias;
    }

    public void setPlazoDias(int plazoDias) {
        this.plazoDias = plazoDias;
    }

    public int getLimitePrestamos() {
        return limitePrestamos;
    }

    public void setLimitePrestamos(int limitePrestamos) {
        this.limitePrestamos = limitePrestamos;
    }

    public int getMaxRenovaciones() {
        return maxRenovaciones;
    }

    public void setMaxRenovaciones(int maxRenovaciones) {
        this.maxRenovaciones = maxRenovaciones;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
}
