
package modelo;

public class TipoUsuario {
    private long idTipoUsuario;
    private String nombre;
    private int limitePrestamos;
    private boolean activo = true;

    public TipoUsuario(String nombre, int limitePrestamos) {
        this.nombre = nombre;
        this.limitePrestamos = limitePrestamos;
    }

    public long getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public int getLimitePrestamos() {
        return limitePrestamos;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setIdTipoUsuario(long idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLimitePrestamos(int limitePrestamos) {
        this.limitePrestamos = limitePrestamos;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
