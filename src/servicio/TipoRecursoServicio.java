
package servicio;

import acceso_datos.TipoRecursoDAO;
import modelo.TipoRecurso;
import java.util.ArrayList;

public class TipoRecursoServicio {
    private TipoRecursoDAO tipoRecursoDAO;
    
    public TipoRecursoServicio(){
        this.tipoRecursoDAO = new TipoRecursoDAO();
    }
    
    public boolean registrar(TipoRecurso tipoRecurso){
        if(tipoRecurso == null){
            return false;
        }
        
        if(tipoRecurso.getNombre() == null || tipoRecurso.getNombre().trim().isEmpty()){
            return false;
        }
        
        return tipoRecursoDAO.registrar(tipoRecurso);
    }   
    
    public TipoRecurso buscarPorId(long idTipoRecurso){
        if(idTipoRecurso <= 0){
            return null;
        }
        
        return tipoRecursoDAO.buscarPorId(idTipoRecurso);
    }
    
    public ArrayList<TipoRecurso> listarTodos(){
        return tipoRecursoDAO.listarTodos();
    }
    
    public boolean actualizar(TipoRecurso tipoRecurso){
        if(tipoRecurso == null){
            return false;
        }
        
        if(tipoRecurso.getIdTipoRecurso() <= 0){
            return false;
        }
        
        if(tipoRecurso.getNombre() == null || tipoRecurso.getNombre().trim().isEmpty()){
            return false;
        }
        
        return tipoRecursoDAO.actualizar(tipoRecurso);
    }
    
    public boolean desactivar(long idTipoRecurso){
        if(idTipoRecurso <= 0){
            return false;
        }
        
        return tipoRecursoDAO.desactivar(idTipoRecurso);
    }
}
