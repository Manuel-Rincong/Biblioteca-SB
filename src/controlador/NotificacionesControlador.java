package controlador;

import java.util.ArrayList;
import modelo.Notificacion;
import modelo.Usuario;
import servicio.NotificacionServicio;
import servicio.UsuarioServicio;
import view.Notificaciones;

public class NotificacionesControlador {

    private final Notificaciones vista;
    private final NotificacionServicio notificacionServicio =
            new NotificacionServicio();
    private final UsuarioServicio usuarioServicio = new UsuarioServicio();

    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public NotificacionesControlador(Notificaciones vista) {
        this.vista = vista;

        cargarUsuarios();

        vista.configurarAcciones(
                e -> consultarNotificaciones(),
                e -> marcarComoLeida()
        );

        vista.configurarCambioFiltro(e -> consultarNotificaciones());
    }

    private void cargarUsuarios() {
        ArrayList<Usuario> resultado = usuarioServicio.listarTodos();

        if (resultado == null) {
            vista.mostrarEstado("No se pudieron cargar los usuarios.");
            return;
        }

        usuarios = resultado;
        String[] nombres = new String[usuarios.size()];

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);

            nombres[i] = usuario.getNombre()
                    + " - " + usuario.getIdentificacion();
        }

        vista.cargarUsuarios(nombres);
    }

    private void consultarNotificaciones() {
        vista.mostrarNotificaciones(new Object[0][4]);

        int indice = vista.getIndiceUsuario();

        if (indice < 0 || indice >= usuarios.size()) {
            vista.mostrarEstado("Selecciona un usuario.");
            return;
        }

        long idUsuario = usuarios.get(indice).getIdUsuario();

        ArrayList<Notificacion> resultado =
                notificacionServicio.listarPorUsuario(idUsuario);

        if (resultado == null) {
            vista.mostrarEstado("No se pudieron consultar las notificaciones.");
            return;
        }

        int filtro = vista.getIndiceFiltro();
        ArrayList<Notificacion> filtradas = new ArrayList<>();

        for (Notificacion notificacion : resultado) {
            if (filtro == 0
                    || (filtro == 1 && !notificacion.isLeida())
                    || (filtro == 2 && notificacion.isLeida())) {
                filtradas.add(notificacion);
            }
        }

        Object[][] filas = new Object[filtradas.size()][4];

        for (int i = 0; i < filtradas.size(); i++) {
            Notificacion notificacion = filtradas.get(i);

            filas[i][0] = notificacion.getIdNotificacion();
            filas[i][1] = notificacion.getFechaCreacion().toString();
            filas[i][2] = notificacion.getMensaje();
            filas[i][3] = notificacion.isLeida();
        }

        vista.mostrarNotificaciones(filas);
    }

    private void marcarComoLeida() {
        Long idNotificacion = vista.getIdNotificacionSeleccionada();

        if (idNotificacion == null) {
            vista.mostrarEstado("Selecciona una notificación en la tabla.");
            return;
        }

        if (!notificacionServicio.marcarComoLeida(idNotificacion)) {
            vista.mostrarEstado(
                    "No se pudo marcar la notificación como leída.");
            return;
        }

        vista.mostrarEstado("Notificación marcada como leída.");
        consultarNotificaciones();
    }
}