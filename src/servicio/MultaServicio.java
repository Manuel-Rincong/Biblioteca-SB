
package servicio;

import java.util.ArrayList;
import acceso_datos.MultaDAO;
import modelo.Multa;
import java.math.BigDecimal;

public class MultaServicio {
    private MultaDAO multaDAO;
    
    public MultaServicio(){
        this.multaDAO = new MultaDAO();
    }
    
    public boolean registrar(Multa multa){
        if(multa == null){
            return false;
        }
        
        if(multa.getIdPrestamo() <= 0){
            return false;
        }
        
        if(multa.getIdPolitica() <= 0){
            return false;
        }
        
        if(multa.getDiasRetraso() <= 0){
            return false;
        }
        
        if(multa.getMontoTotal() == null || multa.getMontoTotal().compareTo(BigDecimal.ZERO) < 0 ){
            return false;
        }
        
        return multaDAO.registrar(multa);
    }
    
    public Multa buscarPorId(long idMulta){
        if(idMulta <= 0){
            return null;
        }
        
        return multaDAO.buscarPorId(idMulta);
    }
    
    public ArrayList<Multa> listarPorPrestamo(long idPrestamo){
        if(idPrestamo <= 0){
            return null;
        }
        
        return multaDAO.listarPorPrestamo(idPrestamo);
    }
    
    public ArrayList<Multa> listarPorUsuario(long idUsuario){
        if(idUsuario <= 0 ){
            return null;
        }
        
        return multaDAO.listarPorUsuario(idUsuario);
    }
    
     public ArrayList<Multa> listarPendientesPorUsuario(long idUsuario){
         if(idUsuario <= 0){
             return null;
         }
         
         return multaDAO.listarPendientesPorUsuario(idUsuario);
     }
     
     public boolean actualizarEstado(long idMulta, String estado){
         if(idMulta <= 0){
             return false;
         }
         
         if(estado == null || estado.trim().isEmpty()){
             return false;
         }
         
         return multaDAO.actualizarEstado(idMulta, estado);
     }
}
