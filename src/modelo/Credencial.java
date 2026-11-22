
package modelo;

public class Credencial {
    private long idCredencial;
    private long idUsuario;
    private String contrasenaHash;
    private boolean activo;

    public Credencial() {
    }

    public Credencial(long idUsuario, String contrasenaHash, boolean activo) {
        this.idUsuario = idUsuario;
        this.contrasenaHash = contrasenaHash;
        this.activo = activo;
    }

    public long getIdCredencial() {
        return idCredencial;
    }

    public void setIdCredencial(long idCredencial) {
        this.idCredencial = idCredencial;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
       
}
