package controlador;

import java.util.ArrayList;
import modelo.TipoUsuario;
import modelo.Usuario;
import servicio.TipoUsuarioServicio;
import servicio.UsuarioServicio;
import view.Usuarios;

public class UsuariosControlador {

    private final Usuarios vista;
    private final UsuarioServicio usuarioServicio = new UsuarioServicio();
    private final TipoUsuarioServicio tipoUsuarioServicio = new TipoUsuarioServicio();

    private ArrayList<TipoUsuario> tiposUsuario = new ArrayList<>();

    public UsuariosControlador(Usuarios vista) {
        this.vista = vista;
        cargarTiposUsuario();

        if (tiposUsuario.isEmpty()) {
            vista.configurarAcciones(null, e -> consultarUsuarios());
        } else {
            vista.configurarAcciones(
                    e -> guardarUsuario(),
                    e -> consultarUsuarios()
            );
        }
    }

    private void cargarTiposUsuario() {
        ArrayList<TipoUsuario> resultado = tipoUsuarioServicio.listarTodos();

        if (resultado == null) {
            vista.mostrarEstado("No se pudieron cargar los tipos de usuario.");
            return;
        }

        tiposUsuario = resultado;
        String[] nombres = new String[tiposUsuario.size()];

        for (int i = 0; i < tiposUsuario.size(); i++) {
            nombres[i] = tiposUsuario.get(i).getNombre();
        }

        vista.cargarTiposUsuario(nombres);

        if (tiposUsuario.isEmpty()) {
            vista.mostrarEstado("No hay tipos de usuario registrados.");
        }
    }

    private void guardarUsuario() {
        String identificacion = vista.getIdentificacion();
        String nombre = vista.getNombreUsuario();
        String correo = vista.getCorreo();
        int indiceTipo = vista.getIndiceTipoUsuario();

        if (identificacion.isEmpty() || nombre.isEmpty() || correo.isEmpty()) {
            vista.mostrarEstado("Completa identificación, nombre y correo.");
            return;
        }

        if (indiceTipo < 0 || indiceTipo >= tiposUsuario.size()) {
            vista.mostrarEstado("Selecciona un tipo de usuario.");
            return;
        }

        long idTipo = tiposUsuario.get(indiceTipo).getIdTipoUsuario();

        Usuario usuario = new Usuario(
                identificacion,
                nombre,
                correo,
                idTipo
        );

        boolean guardado = usuarioServicio.registrar(usuario);

        if (!guardado) {
            vista.mostrarEstado("No se pudo registrar el usuario.");
            return;
        }

        vista.limpiarFormulario();
        vista.mostrarEstado("Usuario registrado correctamente.");
        consultarUsuarios();
    }

    private void consultarUsuarios() {
        ArrayList<Usuario> usuarios = usuarioServicio.listarTodos();

        if (usuarios == null) {
            vista.mostrarEstado("No se pudieron consultar los usuarios.");
            return;
        }

        Object[][] filas = new Object[usuarios.size()][5];

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);

            filas[i][0] = usuario.getIdUsuario();
            filas[i][1] = usuario.getIdentificacion();
            filas[i][2] = usuario.getNombre();
            filas[i][3] = usuario.getCorreo();
            filas[i][4] = buscarNombreTipo(usuario.getIdTipoUsuario());
        }

        vista.mostrarUsuarios(filas);

        if (usuarios.isEmpty()) {
            vista.mostrarEstado("No hay usuarios registrados.");
        }
    }

    private String buscarNombreTipo(long idTipo) {
        for (TipoUsuario tipo : tiposUsuario) {
            if (tipo.getIdTipoUsuario() == idTipo) {
                return tipo.getNombre();
            }
        }

        return "ID " + idTipo;
    }
}
