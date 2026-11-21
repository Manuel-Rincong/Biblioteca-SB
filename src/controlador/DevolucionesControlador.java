package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Prestamo;
import modelo.Recurso;
import modelo.Usuario;
import servicio.PrestamoServicio;
import servicio.RecursoServicio;
import servicio.UsuarioServicio;
import view.Devoluciones;
import java.math.BigDecimal;
import modelo.PoliticaMulta;
import servicio.MultaServicio;
import servicio.PoliticaMultaServicio;

public class DevolucionesControlador {

    private final Devoluciones vista;
    private final PrestamoServicio prestamoServicio = new PrestamoServicio();
    private final UsuarioServicio usuarioServicio = new UsuarioServicio();
    private final RecursoServicio recursoServicio = new RecursoServicio();
    private final MultaServicio multaServicio = new MultaServicio();
    private final PoliticaMultaServicio politicaServicio = new PoliticaMultaServicio();

    private final ArrayList<Prestamo> prestamosPendientes = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Recurso> recursos = new ArrayList<>();

    public DevolucionesControlador(Devoluciones vista) {
        this.vista = vista;

        vista.configurarCambioSeleccion(e -> mostrarDetalle());
        vista.configurarAcciones(e -> devolverPrestamo(), e -> consultarPrestamos());

        consultarPrestamos();
    }

    private void consultarPrestamos() {
        ArrayList<Prestamo> resultado = prestamoServicio.listarTodos();
        ArrayList<Usuario> usuariosConsultados = usuarioServicio.listarTodos();
        ArrayList<Recurso> recursosConsultados = recursoServicio.listarTodos();

        if (resultado == null || usuariosConsultados == null
                || recursosConsultados == null) {
            vista.mostrarEstado("No se pudieron consultar los préstamos pendientes.");
            return;
        }

        usuarios = usuariosConsultados;
        recursos = recursosConsultados;
        prestamosPendientes.clear();

        for (Prestamo prestamo : resultado) {
            if ("ACTIVO".equals(prestamo.getEstado())) {
                prestamosPendientes.add(prestamo);
            }
        }

        String[] descripciones = new String[prestamosPendientes.size()];
        Object[][] filas = new Object[prestamosPendientes.size()][5];

        for (int i = 0; i < prestamosPendientes.size(); i++) {
            Prestamo prestamo = prestamosPendientes.get(i);

            String usuario = buscarNombreUsuario(prestamo.getIdUsuario());
            String recurso = buscarNombreRecurso(prestamo.getIdRecurso());

            descripciones[i] = prestamo.getIdPrestamo() + " - " + recurso;

            filas[i][0] = prestamo.getIdPrestamo();
            filas[i][1] = usuario;
            filas[i][2] = recurso;
            filas[i][3] = prestamo.getFechaLimite().toString();
            filas[i][4] = prestamo.getEstado();
        }

        vista.limpiarFormulario();
        vista.cargarPrestamos(descripciones);
        vista.mostrarPrestamos(filas);

        if (prestamosPendientes.isEmpty()) {
            vista.mostrarEstado("No hay préstamos pendientes de devolución.");
        }
    }

    private void mostrarDetalle() {
    int indice = vista.getIndicePrestamo();

    if (indice < 0 || indice >= prestamosPendientes.size()) {
        return;
    }

    Prestamo prestamo = prestamosPendientes.get(indice);
    LocalDate fechaDevolucion = LocalDate.now();

    vista.mostrarDatosPrestamo(
            buscarNombreUsuario(prestamo.getIdUsuario()),
            buscarNombreRecurso(prestamo.getIdRecurso()),
            prestamo.getFechaLimite()
    );

    vista.mostrarFechaDevolucion(fechaDevolucion);

    int diasRetraso = multaServicio.calcularDiasRetraso(
            prestamo.getFechaLimite(), fechaDevolucion);

    if (diasRetraso < 0) {
        vista.mostrarEstado("No se pudieron calcular los días de retraso.");
        return;
    }

    PoliticaMulta politica = null;

    if (diasRetraso > 0) {
        politica = politicaServicio.buscarActiva();
    }

    BigDecimal monto = multaServicio.calcularMonto(diasRetraso, politica);

    vista.mostrarResultado(diasRetraso, monto);

    if (monto == null) {
        vista.mostrarEstado("No se pudo obtener una política de multa válida.");
        }
    }   

    private void devolverPrestamo() {
        int indice = vista.getIndicePrestamo();

        if (indice < 0 || indice >= prestamosPendientes.size()) {
            vista.mostrarEstado("Selecciona un préstamo para devolver.");
            return;
        }

        Prestamo prestamo = prestamosPendientes.get(indice);
        boolean devuelto = prestamoServicio.devolver(prestamo.getIdPrestamo());

        if (!devuelto) {
            vista.mostrarEstado("No se pudo registrar la devolución.");
            return;
        }

        vista.mostrarEstado("Devolución registrada correctamente.");
        consultarPrestamos();
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