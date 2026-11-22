package controlador;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import javax.swing.SwingWorker;
import modelo.SesionUsuario;
import view.Login;

public class LoginControlador {

    private static final java.util.logging.Logger LOGGER =
            java.util.logging.Logger.getLogger(LoginControlador.class.getName());

    private final Login vista;
    private final AutenticacionControlador autenticacion;
    private final Consumer<SesionUsuario> alIniciarSesion;
    private boolean procesando;

    public LoginControlador(Login vista, AutenticacionControlador autenticacion,
            Consumer<SesionUsuario> alIniciarSesion) {
        this.vista = Objects.requireNonNull(vista);
        this.autenticacion = Objects.requireNonNull(autenticacion);
        this.alIniciarSesion = Objects.requireNonNull(alIniciarSesion);
        vista.configurarAccionIngresar(evento -> iniciarSesion());
    }

    public void mostrar() {
        vista.setVisible(true);
        vista.enfocarCorreo();
    }

    private void iniciarSesion() {
        if (procesando) {
            return;
        }

        vista.limpiarMensaje();
        String correo = vista.getCorreo();
        char[] caracteres = vista.getContrasena();

        if (correo.isEmpty() || caracteres.length == 0) {
            Arrays.fill(caracteres, '\0');
            vista.mostrarError("Escribe tu correo y tu contraseña.");
            if (correo.isEmpty()) {
                vista.enfocarCorreo();
            } else {
                vista.enfocarContrasena();
            }
            return;
        }

        String contrasena = new String(caracteres);
        Arrays.fill(caracteres, '\0');
        vista.limpiarContrasena();
        procesando = true;
        vista.setProcesando(true);

        SwingWorker<SesionUsuario, Void> tarea = new SwingWorker<>() {
            @Override
            protected SesionUsuario doInBackground() throws Exception {
                return autenticacion.iniciarSesion(correo, contrasena);
            }

            @Override
            protected void done() {
                SesionUsuario sesion;
                try {
                    sesion = get();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    vista.mostrarError("Se interrumpió el inicio de sesión. Intenta de nuevo.");
                    return;
                } catch (ExecutionException ex) {
                    LOGGER.log(java.util.logging.Level.WARNING,
                            "No se pudo completar el inicio de sesión.");
                    vista.mostrarError("No fue posible iniciar sesión. Intenta de nuevo.");
                    return;
                } finally {
                    procesando = false;
                    vista.setProcesando(false);
                }

                if (sesion == null) {
                    vista.mostrarError("El correo o la contraseña son incorrectos.");
                    vista.enfocarContrasena();
                    return;
                }

                try {
                    alIniciarSesion.accept(sesion);
                    vista.dispose();
                } catch (RuntimeException ex) {
                    cerrarSesionTrasError();
                }
            }
        };
        tarea.execute();
    }

    private void cerrarSesionTrasError() {
        procesando = true;
        vista.setProcesando(true);
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                autenticacion.cerrarSesion();
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                } catch (ExecutionException ex) {
                    LOGGER.log(java.util.logging.Level.WARNING,
                            "No se pudo cerrar la sesión después de un error al abrir el menú.");
                } finally {
                    procesando = false;
                    vista.setProcesando(false);
                    vista.mostrarError("No se pudo abrir el menú. Revisa la conexión con Principal.");
                }
            }
        }.execute();
    }
}
