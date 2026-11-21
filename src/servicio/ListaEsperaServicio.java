
package servicio;

import java.util.ArrayList;
import acceso_datos.ListaEsperaDAO;
import modelo.ListaEspera;
import acceso_datos.UsuarioDAO;
import acceso_datos.RecursoDAO;
import modelo.Usuario;
import modelo.Recurso;

public class ListaEsperaServicio {
    private ListaEsperaDAO listaEsperaDAO;
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final RecursoDAO recursoDAO = new RecursoDAO();
    
    public ListaEsperaServicio(){
        this.listaEsperaDAO = new ListaEsperaDAO();
    }
    
    public boolean registrar(ListaEspera listaEspera) {
    if (listaEspera == null) {
        return false;
    }

    if (listaEspera.getIdUsuario() <= 0
            || listaEspera.getIdRecurso() <= 0) {
        return false;
    }

    Usuario usuario = usuarioDAO.buscarPorId(listaEspera.getIdUsuario());
    Recurso recurso = recursoDAO.buscarPorId(listaEspera.getIdRecurso());

    if (usuario == null || !usuario.isActivo()
            || recurso == null || !recurso.isActivo()
            || recurso.isDisponible()) {
        return false;
    }

    ArrayList<ListaEspera> inscripciones =
            listaEsperaDAO.listarPorRecurso(listaEspera.getIdRecurso());

    if (inscripciones == null) {
        return false;
    }

    for (ListaEspera inscripcion : inscripciones) {
        if (inscripcion.getIdUsuario() == listaEspera.getIdUsuario()
                && ("ESPERA".equals(inscripcion.getEstado())
                || "NOTIFICADO".equals(inscripcion.getEstado()))) {
            return false;
        }
    }

    return listaEsperaDAO.registrar(listaEspera);
}
    
    public ArrayList<ListaEspera> listarPorRecurso(long idRecurso){
        if(idRecurso <= 0){
            return null;
        }
        
        return listaEsperaDAO.listarPorRecurso(idRecurso);
    }
    
    public ListaEspera buscarSiguienteEnEspera(long idRecurso){
        if(idRecurso <= 0){
            return null;
        }
        
        return listaEsperaDAO.buscarSiguienteEnEspera(idRecurso);
    }
    
    public boolean actualizarEstado(long idListaEspera, String estado){
        if(idListaEspera <= 0){
            return false;
        }
        
        if(estado == null || estado.trim().isEmpty()){
            return false;
        }
        
        return listaEsperaDAO.actualizarEstado(idListaEspera, estado);
    }
    
    public boolean cancelar(long idListaEspera){
        if(idListaEspera <= 0){
            return false;
        }
        
        return listaEsperaDAO.cancelar(idListaEspera);
    }
    
}
