
package modelo;

public class Usuario {
    private long idUsuario;
    private String identificacion;
    private String nombre;
    private String correo;
    private long idTipoUsuario;
    private boolean activo = true;

    public Usuario(String identificacion, String nombre, String correo, long idTipoUsuario) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correo = correo;
        this.idTipoUsuario = idTipoUsuario;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public long getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setIdTipoUsuario(long idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
