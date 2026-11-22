package controlador;

import java.util.Objects;
import modelo.SesionUsuario;
import servicio.AutenticacionServicio;

public class AutenticacionControlador {

    private final AutenticacionServicio servicio;

    public AutenticacionControlador() {
        this(new AutenticacionServicio());
    }

    public AutenticacionControlador(AutenticacionServicio servicio) {
        this.servicio = Objects.requireNonNull(servicio);
    }

    public SesionUsuario iniciarSesion(String correo, String contrasena) throws Exception {
        return servicio.iniciarSesion(correo, contrasena);
    }

    public void cerrarSesion() throws Exception {
        servicio.cerrarSesion();
    }

    public SesionUsuario getSesionActual() throws Exception {
        return servicio.getSesionActual();
    }
}
