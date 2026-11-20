package controlador;

import modelo.Usuario;
import servicio.UsuarioServicio;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import modelo.TipoUsuario;
import servicio.TipoUsuarioServicio;
import view.Usuarios;

public class UsuariosControlador {
    
private final Usuarios vista;
    private final TipoUsuarioServicio tipoUsuarioServicio;
    private final UsuarioServicio usuarioServicio = new UsuarioServicio();
    private ArrayList<TipoUsuario> tiposUsuario;

    public UsuariosControlador(Usuarios vista) {
        this.vista = vista;
        tipoUsuarioServicio = new TipoUsuarioServicio();
        cargarTiposUsuario();
    }

    private void cargarTiposUsuario() {
        SwingWorker<ArrayList<TipoUsuario>, Void> tarea = new SwingWorker<>() {
            @Override
            protected ArrayList<TipoUsuario> doInBackground() {
                return tipoUsuarioServicio.listarTodos();
            }

            @Override
            protected void done() {
                try {
                    tiposUsuario = get();

                    if (tiposUsuario == null) {
                        vista.mostrarEstado("No se pudieron consultar los tipos de usuario.");
                        return;
                    }

                    String[] nombres = new String[tiposUsuario.size()];

                    for (int i = 0; i < tiposUsuario.size(); i++) {
                        nombres[i] = tiposUsuario.get(i).getNombre();
                    }

                    vista.cargarTiposUsuario(nombres);

                    if (tiposUsuario.isEmpty()) {
                        vista.mostrarEstado("No hay tipos de usuario registrados.");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    vista.mostrarEstado("La carga de tipos de usuario se interrumpió.");
                } catch (ExecutionException e) {
                    vista.mostrarEstado("No se pudieron cargar los tipos de usuario.");
                }
            }
        };

        tarea.execute();
    }
}
