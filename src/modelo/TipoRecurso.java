
package modelo;

public class TipoRecurso {
    private long idTipoRecurso;
    private String nombre;
    private String descripcion;
    private boolean activo;

    public TipoRecurso(String nombre) {
        this.nombre = nombre;
    }

    public TipoRecurso(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public long getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(long idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
