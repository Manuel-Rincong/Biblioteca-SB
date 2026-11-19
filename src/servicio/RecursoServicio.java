
package servicio;

import modelo.Recurso;
import acceso_datos.RecursoDAO;
import java.util.ArrayList;

public class RecursoServicio {
    
    private RecursoDAO recursoDAO;
    
    public RecursoServicio(){
        this.recursoDAO = new RecursoDAO();
    }
    
    public boolean registrar(Recurso recurso){
        if(recurso == null){
            return false;
        }
        
        if(recurso.getIdentificador() == null || recurso.getIdentificador().trim().isEmpty()){
            return false;
        }
        
        if(recurso.getNombre() == null || recurso.getNombre().trim().isEmpty()){
            return false;
        }
        
        if(recurso.getIdTipoRecurso() <= 0){
            return false;
        }
        
        return recursoDAO.registrar(recurso);
    }
    
    public Recurso buscarPorId(long idRecurso){
        if(idRecurso <= 0){
            return null;
        }
        
        return recursoDAO.buscarPorId(idRecurso);
    }
    
    public ArrayList<Recurso> listarTodos(){
        return recursoDAO.listarTodos();
    }
    
    public boolean actualizar(Recurso recurso){
        if(recurso == null){
            return false;
        }
        
        if(recurso.getIdRecurso() <= 0){
            return false;
        }
        
        if(recurso.getIdentificador() == null || recurso.getIdentificador().trim().isEmpty()){
            return false;
        }
        
        if(recurso.getNombre() == null || recurso.getNombre().trim().isEmpty()){
            return false;
        }
        
        if(recurso.getIdTipoRecurso() <= 0){
            return false;
        }
        
        return recursoDAO.actualizar(recurso);
    }
    
    public boolean desactivar(long idRecurso){
        if(idRecurso <= 0){
            return false;
        }
        
        return recursoDAO.desactivar(idRecurso);
    }
    
}
