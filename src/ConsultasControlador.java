package controlador;

import java.util.ArrayList;
import modelo.Prestamo;
import modelo.Recurso;
import modelo.Usuario;
import servicio.PrestamoServicio;
import servicio.RecursoServicio;
import servicio.UsuarioServicio;
import view.Consultas;

public class ConsultasControlador {

    private final Consultas vista;
    private final PrestamoServicio prestamoServicio = new PrestamoServicio();
    private final UsuarioServicio usuarioServicio = new UsuarioServicio();
    private final RecursoServicio recursoServicio = new RecursoServicio();

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Recurso> recursos = new ArrayList<>();

    public ConsultasControlador(Consultas vista) {
        this.vista = vista;

        cargarUsuarios();
        cargarRecursos();

        vista.configurarAccionConsulta(e -> consultar());
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

    private void cargarRecursos() {
        ArrayList<Recurso> resultado = recursoServicio.listarTodos();

        if (resultado == null) {
            vista.mostrarEstado("No se pudieron cargar los recursos.");
            return;
        }

        recursos = resultado;
    }

    private void consultar() {
        vista.mostrarPrestamos(new Object[0][8]);

        int tipo = vista.getTipoConsulta();
        ArrayList<Prestamo> resultado;

        if (tipo == 0) {
            resultado = prestamoServicio.listarTodos();
        } else if (tipo == 1) {
            resultado = prestamoServicio.listarVencidos();
        } else if (tipo == 2) {
            int indice = vista.getIndiceUsuario();

            if (indice < 0 || indice >= usuarios.size()) {
                vista.mostrarEstado(
                        "Selecciona un usuario para consultar su historial.");
                return;
            }

            long idUsuario = usuarios.get(indice).getIdUsuario();
            resultado = prestamoServicio.listarPorUsuario(idUsuario);
        } else {
            vista.mostrarEstado("Selecciona un tipo de consulta.");
            return;
        }

        if (resultado == null) {
            vista.mostrarEstado("No se pudieron consultar los préstamos.");
            return;
        }

        ArrayList<Prestamo> prestamos = new ArrayList<>();

        for (Prestamo prestamo : resultado) {
            if (tipo != 0 || "ACTIVO".equals(prestamo.getEstado())) {
                prestamos.add(prestamo);
            }
        }

        Object[][] filas = new Object[prestamos.size()][8];

        for (int i = 0; i < prestamos.size(); i++) {
            Prestamo prestamo = prestamos.get(i);

            filas[i][0] = prestamo.getIdPrestamo();
            filas[i][1] = buscarNombreUsuario(prestamo.getIdUsuario());
            filas[i][2] = buscarNombreRecurso(prestamo.getIdRecurso());
            filas[i][3] = prestamo.getFechaPrestamo().toString();
            filas[i][4] = prestamo.getFechaLimite().toString();

            filas[i][5] = prestamo.getFechaDevolucion() == null
                    ? "" : prestamo.getFechaDevolucion().toString();

            filas[i][6] = prestamo.getRenovacionesRealizadas();
            filas[i][7] = prestamo.getEstado();
        }

        vista.mostrarPrestamos(filas);

        if (prestamos.isEmpty()) {
            vista.mostrarEstado("No hay préstamos para esta consulta.");
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