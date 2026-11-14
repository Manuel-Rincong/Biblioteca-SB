
package modelo;

public class Recurso {
    private long idRecurso;
    private String identificador;
    private String nombre;
    private long idTipoRecurso;
    private String descripcion;
    private boolean disponible = true;
    private boolean activo = true;

    public Recurso(String identificador, String nombre, long idTipoRecurso) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.idTipoRecurso = idTipoRecurso;
    }

    public Recurso(String identificador, String nombre, long idTipoRecurso, String descripcion) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.idTipoRecurso = idTipoRecurso;
        this.descripcion = descripcion;
    }

    public long getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(long idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(long idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
