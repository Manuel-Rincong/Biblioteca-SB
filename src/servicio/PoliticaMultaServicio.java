
package servicio;

import java.util.ArrayList;
import acceso_datos.PoliticaMultaDAO;
import modelo.PoliticaMulta;
import java.math.BigDecimal;

public class PoliticaMultaServicio {
    private PoliticaMultaDAO politicaMultaDAO;
    
    public PoliticaMultaServicio(){
        this.politicaMultaDAO = new PoliticaMultaDAO();
    }
    
    public boolean registrar(PoliticaMulta politicaMulta){
        if(politicaMulta == null){
            return false;
        }
        
        if(politicaMulta.getNombre() == null || politicaMulta.getNombre().trim().isEmpty()){
            return false;
        }
        
        if(politicaMulta.getDiasGracia()< 0){
            return false;
        }
        
        if(politicaMulta.getValorPorDia() == null || politicaMulta.getValorPorDia().compareTo(BigDecimal.ZERO) < 0){
        return false;
        }

        if(politicaMulta.getMultaMaxima() != null && politicaMulta.getMultaMaxima().compareTo(BigDecimal.ZERO) < 0){
            return false;
        }
        
        return politicaMultaDAO.registrar(politicaMulta);
    }
    
    public PoliticaMulta buscarPorId(long idPolitica){
        if(idPolitica <= 0){
            return null;
        }
        
        return politicaMultaDAO.buscarPorId(idPolitica);
    }
    
    public ArrayList<PoliticaMulta> listarTodos(){
        return politicaMultaDAO.listarTodos();
    }
    
    public boolean actualizar(PoliticaMulta politicaMulta){
        if(politicaMulta == null){
            return false;
        }
        
        if(politicaMulta.getIdPolitica() <= 0){
            return false;
        }
        
        if(politicaMulta.getNombre() == null || politicaMulta.getNombre().trim().isEmpty()){
            return false;
        }
        
        if(politicaMulta.getDiasGracia()< 0){
            return false;
        }
        
        if(politicaMulta.getValorPorDia() == null || politicaMulta.getValorPorDia().compareTo(BigDecimal.ZERO) < 0){
        return false;
        }

        if(politicaMulta.getMultaMaxima() != null && politicaMulta.getMultaMaxima().compareTo(BigDecimal.ZERO) < 0){
            return false;
        }
        
        return politicaMultaDAO.actualizar(politicaMulta);
    }
    
    public boolean desactivar(long idPolitica){
        if(idPolitica <= 0){
            return false;
        }
        
        return politicaMultaDAO.desactivar(idPolitica);
    }
    
    public PoliticaMulta buscarActiva(){
        return politicaMultaDAO.buscarActiva();
    }
}
