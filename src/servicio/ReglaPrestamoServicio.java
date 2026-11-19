
package servicio;

import modelo.ReglaPrestamo;
import acceso_datos.ReglaPrestamoDAO;
import java.util.ArrayList;

public class ReglaPrestamoServicio {
    private ReglaPrestamoDAO reglaPrestamoDAO;
    
    public ReglaPrestamoServicio(){
        this.reglaPrestamoDAO = new ReglaPrestamoDAO();
    }
    
    public boolean registrar(ReglaPrestamo reglaPrestamo){
        if(reglaPrestamo == null){
            return false;
        }
        
        if(reglaPrestamo.getIdTipoUsuario() <= 0){
            return false;
        }
        
        if(reglaPrestamo.getIdTipoRecurso() <= 0){
            return false;
        }
        
        if(reglaPrestamo.getPlazoDias() <= 0){
            return false;
        }
        
        if(reglaPrestamo.getLimitePrestamos() <= 0){
            return false;
        }
        
        if(reglaPrestamo.getMaxRenovaciones() <0){
            return false;
        }
        
        return reglaPrestamoDAO.registrar(reglaPrestamo);
    }
    
    public ReglaPrestamo buscarPorTipoUsuarioYRecurso(long idTipoUsuario, long idTipoRecurso){
        if(idTipoUsuario <= 0 || idTipoRecurso <= 0){
            return null;
        }
        
        return reglaPrestamoDAO.buscarPorTipoUsuarioYRecurso(idTipoUsuario, idTipoRecurso);
    }
    
    public ReglaPrestamo buscarPorId(long idRegla){
        if(idRegla <= 0){
            return null;
        }
        
        return reglaPrestamoDAO.buscarPorId(idRegla);
    }
    
    public ArrayList<ReglaPrestamo> listarTodos(){
        return reglaPrestamoDAO.listarTodos();
    }
    
    public boolean actualizar(ReglaPrestamo reglaPrestamo){
        if(reglaPrestamo == null){
            return false;
        }
        
        if(reglaPrestamo.getIdRegla() <= 0){
            return false;
        }
        
        if(reglaPrestamo.getIdTipoUsuario() <= 0){
            return false;
        }
        
        if(reglaPrestamo.getIdTipoRecurso() <= 0){
            return false;
        }
        
        if(reglaPrestamo.getPlazoDias() <= 0){
            return false;
        }
        
        if(reglaPrestamo.getLimitePrestamos() <= 0){
            return false;
        }
        
        if(reglaPrestamo.getMaxRenovaciones() <0){
            return false;
        }
        
        return reglaPrestamoDAO.actualizar(reglaPrestamo);
    }
    
    public boolean desactivar(long idRegla){
        if(idRegla <= 0){
            return false;
        }
        
        return reglaPrestamoDAO.desactivar(idRegla);
    }
}
