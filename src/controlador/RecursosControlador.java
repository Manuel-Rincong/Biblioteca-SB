package controlador;

import java.util.ArrayList;
import modelo.Recurso;
import modelo.TipoRecurso;
import servicio.RecursoServicio;
import servicio.TipoRecursoServicio;
import view.Recursos;

public class RecursosControlador {

    private final Recursos vista;
    private final RecursoServicio recursoServicio = new RecursoServicio();
    private final TipoRecursoServicio tipoRecursoServicio = new TipoRecursoServicio();

    private ArrayList<TipoRecurso> tiposRecurso = new ArrayList<>();

    public RecursosControlador(Recursos vista) {
        this.vista = vista;
        cargarTiposRecurso();

        if (tiposRecurso.isEmpty()) {
            vista.configurarAcciones(null, e -> consultarRecursos());
        } else {
            vista.configurarAcciones(
                    e -> guardarRecurso(),
                    e -> consultarRecursos()
            );
        }
    }

    private void cargarTiposRecurso() {
        ArrayList<TipoRecurso> resultado = tipoRecursoServicio.listarTodos();

        if (resultado == null) {
            vista.mostrarEstado("No se pudieron cargar los tipos de recurso.");
            return;
        }

        tiposRecurso = resultado;
        String[] nombres = new String[tiposRecurso.size()];

        for (int i = 0; i < tiposRecurso.size(); i++) {
            nombres[i] = tiposRecurso.get(i).getNombre();
        }

        vista.cargarTiposRecurso(nombres);

        if (tiposRecurso.isEmpty()) {
            vista.mostrarEstado("No hay tipos de recurso registrados.");
        }
    }

    private void guardarRecurso() {
        String identificador = vista.getIdentificador();
        String nombre = vista.getNombreRecurso();
        String descripcion = vista.getDescripcion();
        int indiceTipo = vista.getIndiceTipoRecurso();

        if (identificador.isEmpty() || nombre.isEmpty()) {
            vista.mostrarEstado("Completa el identificador y el nombre del recurso.");
            return;
        }

        if (indiceTipo < 0 || indiceTipo >= tiposRecurso.size()) {
            vista.mostrarEstado("Selecciona un tipo de recurso.");
            return;
        }

        long idTipo = tiposRecurso.get(indiceTipo).getIdTipoRecurso();

        Recurso recurso = new Recurso(
                identificador,
                nombre,
                idTipo,
                descripcion
        );

        boolean guardado = recursoServicio.registrar(recurso);

        if (!guardado) {
            vista.mostrarEstado("No se pudo registrar el recurso.");
            return;
        }

        vista.limpiarFormulario();
        vista.mostrarEstado("Recurso registrado correctamente.");
        consultarRecursos();
    }

    private void consultarRecursos() {
        ArrayList<Recurso> recursos = recursoServicio.listarTodos();

        if (recursos == null) {
            vista.mostrarEstado("No se pudieron consultar los recursos.");
            return;
        }

        Object[][] filas = new Object[recursos.size()][6];

        for (int i = 0; i < recursos.size(); i++) {
            Recurso recurso = recursos.get(i);

            filas[i][0] = recurso.getIdRecurso();
            filas[i][1] = recurso.getIdentificador();
            filas[i][2] = recurso.getNombre();
            filas[i][3] = buscarNombreTipo(recurso.getIdTipoRecurso());
            filas[i][4] = recurso.isDisponible();
            filas[i][5] = recurso.getDescripcion();
        }

        vista.mostrarRecursos(filas);

        if (recursos.isEmpty()) {
            vista.mostrarEstado("No hay recursos registrados.");
        }
    }

    private String buscarNombreTipo(long idTipo) {
        for (TipoRecurso tipo : tiposRecurso) {
            if (tipo.getIdTipoRecurso() == idTipo) {
                return tipo.getNombre();
            }
        }

        return "ID " + idTipo;
    }
}