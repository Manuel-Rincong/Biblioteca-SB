
package servicio;

import java.util.ArrayList;
import acceso_datos.ListaEsperaDAO;
import modelo.ListaEspera;

public class ListaEsperaServicio {
    private ListaEsperaDAO listaEsperaDAO;
    
    public ListaEsperaServicio(){
        this.listaEsperaDAO = new ListaEsperaDAO();
    }
    
    public boolean registrar(ListaEspera listaEspera){
        if(listaEspera == null){
            return false;
        }
        
        if(listaEspera.getIdUsuario() <= 0){
            return false;
        }
        
        if(listaEspera.getIdRecurso() <= 0){
            return false;
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
