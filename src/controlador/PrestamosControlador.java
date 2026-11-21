package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Prestamo;
import modelo.Recurso;
import modelo.ReglaPrestamo;
import modelo.Usuario;
import servicio.PrestamoServicio;
import servicio.RecursoServicio;
import servicio.ReglaPrestamoServicio;
import servicio.UsuarioServicio;
import view.Prestamos;

public class PrestamosControlador {

    private final Prestamos vista;
    private final UsuarioServicio usuarioServicio = new UsuarioServicio();
    private final RecursoServicio recursoServicio = new RecursoServicio();
    private final PrestamoServicio prestamoServicio = new PrestamoServicio();
    private final ReglaPrestamoServicio reglaServicio = new ReglaPrestamoServicio();

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Recurso> recursos = new ArrayList<>();

    public PrestamosControlador(Prestamos vista) {
        this.vista = vista;

        cargarUsuarios();
        cargarRecursos();

        vista.configurarCambioSeleccion(e -> calcularFechaLimite(LocalDate.now()));
        vista.configurarAcciones(e -> registrarPrestamo(), e -> consultarPrestamos());
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
            nombres[i] = usuario.getNombre() + " - " + usuario.getIdentificacion();
        }

        vista.cargarUsuarios(nombres);
    }

    private void cargarRecursos() {
        ArrayList<Recurso> resultado = recursoServicio.listarTodos();

        if (resultado == null) {
            vista.mostrarEstado("No se pudieron cargar los recursos.");
            return;
        }

        recursos = resultado;
        String[] nombres = new String[recursos.size()];

        for (int i = 0; i < recursos.size(); i++) {
            Recurso recurso = recursos.get(i);
            nombres[i] = recurso.getNombre() + " - " + recurso.getIdentificador();
        }

        vista.cargarRecursos(nombres);
    }

    private LocalDate calcularFechaLimite(LocalDate fechaPrestamo) {
        vista.mostrarFechaPrestamo(fechaPrestamo);
        vista.mostrarFechaLimite(null);

        int indiceUsuario = vista.getIndiceUsuario();
        int indiceRecurso = vista.getIndiceRecurso();

        if (indiceUsuario < 0 || indiceUsuario >= usuarios.size()
                || indiceRecurso < 0 || indiceRecurso >= recursos.size()) {
            vista.mostrarEstado("Selecciona un usuario y un recurso.");
            return null;
        }

        Usuario usuario = usuarios.get(indiceUsuario);
        Recurso recurso = recursos.get(indiceRecurso);

        if (!usuario.isActivo()) {
            vista.mostrarEstado("El usuario seleccionado está inactivo.");
            return null;
        }

        if (!recurso.isActivo() || !recurso.isDisponible()) {
            vista.mostrarEstado("El recurso seleccionado no está disponible para préstamo.");
            return null;
        }

        ReglaPrestamo regla = reglaServicio.buscarPorTipoUsuarioYRecurso(
                usuario.getIdTipoUsuario(), recurso.getIdTipoRecurso());

        if (regla == null || !regla.isActiva() || regla.getPlazoDias() <= 0) {
            vista.mostrarEstado("No se pudo obtener una regla de préstamo válida para esta selección.");
            return null;
        }

        LocalDate fechaLimite = fechaPrestamo.plusDays(regla.getPlazoDias());

        vista.mostrarFechaLimite(fechaLimite);
        return fechaLimite;
    }

    private void registrarPrestamo() {
        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaLimite = calcularFechaLimite(fechaPrestamo);

        if (fechaLimite == null) {
            return;
        }

        Usuario usuario = usuarios.get(vista.getIndiceUsuario());
        Recurso recurso = recursos.get(vista.getIndiceRecurso());

        Prestamo prestamo = new Prestamo(
                usuario.getIdUsuario(),
                recurso.getIdRecurso(),
                fechaPrestamo,
                fechaLimite,
                "ACTIVO"
        );

        boolean guardado = prestamoServicio.registrar(prestamo);

        if (!guardado) {
            vista.mostrarEstado("No se pudo registrar el préstamo. Revisa la disponibilidad, el límite de préstamos y la conexión.");
            return;
        }

        vista.limpiarFormulario();
        vista.mostrarEstado("Préstamo registrado correctamente.");

        cargarRecursos();
        consultarPrestamos();
    }

    private void consultarPrestamos() {
        ArrayList<Prestamo> prestamos = prestamoServicio.listarTodos();

        if (prestamos == null) {
            vista.mostrarEstado("No se pudieron consultar los préstamos.");
            return;
        }

        Object[][] filas = new Object[prestamos.size()][6];

        for (int i = 0; i < prestamos.size(); i++) {
            Prestamo prestamo = prestamos.get(i);

            filas[i][0] = prestamo.getIdPrestamo();
            filas[i][1] = buscarNombreUsuario(prestamo.getIdUsuario());
            filas[i][2] = buscarNombreRecurso(prestamo.getIdRecurso());
            filas[i][3] = prestamo.getFechaPrestamo().toString();
            filas[i][4] = prestamo.getFechaLimite().toString();
            filas[i][5] = prestamo.getEstado();
        }

        vista.mostrarPrestamos(filas);

        if (prestamos.isEmpty()) {
            vista.mostrarEstado("No hay préstamos registrados.");
        }
    }

    private String buscarNombreUsuario(long idUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario() == idUsuario) {
                return usuario.getNombre() + " - " + usuario.getIdentificacion();
            }
        }

        return "ID " + idUsuario;
    }

    private String buscarNombreRecurso(long idRecurso) {
        for (Recurso recurso : recursos) {
            if (recurso.getIdRecurso() == idRecurso) {
                return recurso.getNombre() + " - " + recurso.getIdentificador();
            }
        }

        return "ID " + idRecurso;
    }
}