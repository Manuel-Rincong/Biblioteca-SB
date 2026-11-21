package controlador;

import java.util.ArrayList;
import modelo.ListaEspera;
import modelo.Recurso;
import modelo.Usuario;
import servicio.ListaEsperaServicio;
import servicio.RecursoServicio;
import servicio.UsuarioServicio;
import view.ListaDeEspera;

public class ListaDeEsperaControlador {

    private final ListaDeEspera vista;
    private final ListaEsperaServicio listaServicio = new ListaEsperaServicio();
    private final UsuarioServicio usuarioServicio = new UsuarioServicio();
    private final RecursoServicio recursoServicio = new RecursoServicio();

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Recurso> recursos = new ArrayList<>();

    public ListaDeEsperaControlador(ListaDeEspera vista) {
        this.vista = vista;

        cargarUsuarios();
        cargarRecursos();

        vista.configurarCambioSeleccion(e -> consultarLista());

        vista.configurarAcciones(
                e -> inscribirUsuario(),
                e -> retirarInscripcion(),
                e -> consultarLista()
        );
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

    private void consultarLista() {
        vista.mostrarListaEspera(new Object[0][5]);

        int indice = vista.getIndiceRecurso();

        if (indice < 0 || indice >= recursos.size()) {
            vista.mostrarEstado("Selecciona un recurso para consultar su lista.");
            return;
        }

        long idRecurso = recursos.get(indice).getIdRecurso();
        Recurso recurso = recursoServicio.buscarPorId(idRecurso);

        ArrayList<ListaEspera> resultado =
                listaServicio.listarPorRecurso(idRecurso);

        if (recurso == null || resultado == null) {
            vista.mostrarEstado("No se pudo consultar la lista de espera.");
            return;
        }

        recursos.set(indice, recurso);

        ArrayList<ListaEspera> pendientes = new ArrayList<>();

        for (ListaEspera inscripcion : resultado) {
            if ("ESPERA".equals(inscripcion.getEstado())) {
                pendientes.add(inscripcion);
            }
        }

        Object[][] filas = new Object[pendientes.size()][5];

        for (int i = 0; i < pendientes.size(); i++) {
            ListaEspera inscripcion = pendientes.get(i);

            filas[i][0] = inscripcion.getIdListaEspera();
            filas[i][1] = i + 1;
            filas[i][2] = buscarNombreUsuario(inscripcion.getIdUsuario());
            filas[i][3] = inscripcion.getFechaInscripcion().toString();
            filas[i][4] = inscripcion.getEstado();
        }

        String estado = "Inactivo";

        if (recurso.isActivo()) {
            estado = recurso.isDisponible() ? "Disponible" : "No disponible";
        }

        vista.mostrarDatosRecurso(estado, pendientes.size());
        vista.mostrarListaEspera(filas);

        if (pendientes.isEmpty()) {
            vista.mostrarEstado("No hay usuarios en espera para este recurso.");
        } else {
            vista.mostrarEstado("");
        }
    }

    private void inscribirUsuario() {
        int indiceUsuario = vista.getIndiceUsuario();
        int indiceRecurso = vista.getIndiceRecurso();

        if (indiceUsuario < 0 || indiceUsuario >= usuarios.size()
                || indiceRecurso < 0 || indiceRecurso >= recursos.size()) {
            vista.mostrarEstado("Selecciona un usuario y un recurso.");
            return;
        }

        ListaEspera inscripcion = new ListaEspera(
                usuarios.get(indiceUsuario).getIdUsuario(),
                recursos.get(indiceRecurso).getIdRecurso()
        );

        if (!listaServicio.registrar(inscripcion)) {
            vista.mostrarEstado(
                    "No se pudo inscribir: revisa disponibilidad, usuario e inscripciones activas.");
            return;
        }

        consultarLista();
        vista.mostrarEstado("Usuario inscrito correctamente.");
    }

    private void retirarInscripcion() {
        Long idInscripcion = vista.getIdInscripcionSeleccionada();

        if (idInscripcion == null) {
            vista.mostrarEstado("Selecciona una inscripción en la tabla.");
            return;
        }

        if (!listaServicio.cancelar(idInscripcion)) {
            vista.mostrarEstado("No se pudo retirar la inscripción.");
            return;
        }

        consultarLista();
        vista.mostrarEstado("Inscripción retirada correctamente.");
    }

    private String buscarNombreUsuario(long idUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario() == idUsuario) {
                return usuario.getNombre() + " - " + usuario.getIdentificacion();
            }
        }

        return "ID " + idUsuario;
    }
}