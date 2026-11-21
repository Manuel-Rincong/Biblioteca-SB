package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Prestamo;
import modelo.Recurso;
import modelo.ReglaPrestamo;
import modelo.Renovacion;
import modelo.Usuario;
import servicio.PrestamoServicio;
import servicio.RecursoServicio;
import servicio.ReglaPrestamoServicio;
import servicio.UsuarioServicio;
import view.Renovaciones;

public class RenovacionesControlador {

    private final Renovaciones vista;
    private final PrestamoServicio prestamoServicio = new PrestamoServicio();
    private final UsuarioServicio usuarioServicio = new UsuarioServicio();
    private final RecursoServicio recursoServicio = new RecursoServicio();
    private final ReglaPrestamoServicio reglaServicio = new ReglaPrestamoServicio();

    private final ArrayList<Prestamo> prestamosActivos = new ArrayList<>();

    public RenovacionesControlador(Renovaciones vista) {
        this.vista = vista;

        vista.configurarCambioSeleccion(e -> mostrarDetalle());
        vista.configurarAcciones(e -> renovarPrestamo(), e -> consultar());

        cargarPrestamos();
    }

    private void cargarPrestamos() {
        vista.habilitarRenovacion(false);

        ArrayList<Prestamo> resultado = prestamoServicio.listarTodos();

        if (resultado == null) {
            vista.mostrarEstado("No se pudieron cargar los préstamos.");
            return;
        }

        prestamosActivos.clear();

        for (Prestamo prestamo : resultado) {
            if ("ACTIVO".equals(prestamo.getEstado())) {
                prestamosActivos.add(prestamo);
            }
        }

        String[] descripciones = new String[prestamosActivos.size()];

        for (int i = 0; i < prestamosActivos.size(); i++) {
            Prestamo prestamo = prestamosActivos.get(i);

            descripciones[i] = "Préstamo " + prestamo.getIdPrestamo()
                    + " - recurso " + prestamo.getIdRecurso();
        }

        vista.cargarPrestamos(descripciones);
        vista.mostrarRenovaciones(new Object[0][5]);

        if (prestamosActivos.isEmpty()) {
            vista.mostrarEstado("No hay préstamos activos.");
        } else {
            vista.mostrarEstado("");
        }
    }

    private void consultar() {
        if (vista.getIndicePrestamo() < 0) {
            cargarPrestamos();
        } else {
            mostrarDetalle();
        }
    }

    private void mostrarDetalle() {
        vista.habilitarRenovacion(false);
        vista.mostrarNuevaFechaLimite(null);
        vista.mostrarRenovaciones(new Object[0][5]);
        vista.mostrarEstado("");

        int indice = vista.getIndicePrestamo();

        if (indice < 0 || indice >= prestamosActivos.size()) {
            vista.mostrarEstado("Selecciona un préstamo.");
            return;
        }

        long idPrestamo = prestamosActivos.get(indice).getIdPrestamo();
        Prestamo prestamo = prestamoServicio.buscarPorId(idPrestamo);

        if (prestamo == null) {
            vista.mostrarEstado("No se pudieron consultar los datos del préstamo.");
            return;
        }

        prestamosActivos.set(indice, prestamo);

        if (!mostrarHistorial(idPrestamo)) {
            return;
        }

        Usuario usuario = usuarioServicio.buscarPorId(prestamo.getIdUsuario());
        Recurso recurso = recursoServicio.buscarPorId(prestamo.getIdRecurso());

        if (usuario == null || recurso == null) {
            vista.mostrarEstado("No se pudieron consultar el usuario y el recurso.");
            return;
        }

        ReglaPrestamo regla = reglaServicio.buscarPorTipoUsuarioYRecurso(
                usuario.getIdTipoUsuario(), recurso.getIdTipoRecurso());

        if (regla == null || !regla.isActiva() || regla.getPlazoDias() <= 0) {
            vista.mostrarEstado("No se pudo obtener una regla de préstamo válida.");
            return;
        }

        vista.mostrarDatosPrestamo(
                usuario.getNombre() + " - " + usuario.getIdentificacion(),
                recurso.getNombre() + " - " + recurso.getIdentificador(),
                prestamo.getFechaLimite(),
                prestamo.getRenovacionesRealizadas(),
                regla.getMaxRenovaciones()
        );

        if (!"ACTIVO".equals(prestamo.getEstado())
                || !usuario.isActivo() || !recurso.isActivo()) {
            vista.mostrarEstado("El préstamo, el usuario o el recurso no está activo.");
            return;
        }

        if (prestamo.getFechaLimite() == null
                || prestamo.getFechaLimite().isBefore(LocalDate.now())) {
            vista.mostrarEstado("El préstamo no tiene una fecha válida para renovar.");
            return;
        }

        if (prestamo.getRenovacionesRealizadas() >= regla.getMaxRenovaciones()) {
            vista.mostrarEstado("El préstamo alcanzó el límite de renovaciones.");
            return;
        }

        vista.mostrarNuevaFechaLimite(
                prestamo.getFechaLimite().plusDays(regla.getPlazoDias()));

        vista.habilitarRenovacion(true);
    }

    private boolean mostrarHistorial(long idPrestamo) {
        ArrayList<Renovacion> renovaciones =
                prestamoServicio.listarRenovaciones(idPrestamo);

        if (renovaciones == null) {
            vista.mostrarEstado("No se pudo consultar el historial de renovaciones.");
            return false;
        }

        Object[][] filas = new Object[renovaciones.size()][5];

        for (int i = 0; i < renovaciones.size(); i++) {
            Renovacion renovacion = renovaciones.get(i);

            filas[i][0] = renovacion.getIdRenovacion();
            filas[i][1] = renovacion.getIdPrestamo();
            filas[i][2] = renovacion.getFechaLimiteAnterior().toString();
            filas[i][3] = renovacion.getNuevaFechaLimite().toString();
            filas[i][4] = renovacion.getFechaRenovacion().toString();
        }

        vista.mostrarRenovaciones(filas);
        return true;
    }

    private void renovarPrestamo() {
        int indice = vista.getIndicePrestamo();

        if (indice < 0 || indice >= prestamosActivos.size()) {
            vista.mostrarEstado("Selecciona un préstamo para renovar.");
            return;
        }

        long idPrestamo = prestamosActivos.get(indice).getIdPrestamo();
        boolean renovado = prestamoServicio.renovar(idPrestamo);

        if (!renovado) {
            vista.mostrarEstado(
                    "No se pudo renovar: revisa vencimiento, límite y lista de espera.");
            return;
        }

        mostrarDetalle();
        vista.mostrarEstado("Préstamo renovado correctamente.");
    }
}