package servicio;

import java.util.ArrayList;
import acceso_datos.MultaDAO;
import modelo.Multa;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import modelo.PoliticaMulta;

public class MultaServicio {
    private MultaDAO multaDAO;
    private CalculadoraMulta calculadoraMulta;
    
    
    public MultaServicio(){
        this.multaDAO = new MultaDAO();
        this.calculadoraMulta = new CalculadoraMulta(new MultaPorDia());
    }
    
    public int calcularDiasRetraso(LocalDate fechaLimite, LocalDate fechaDevolucion) {
        if (fechaLimite == null || fechaDevolucion == null) {
            return -1;
        }

        long dias = ChronoUnit.DAYS.between(fechaLimite, fechaDevolucion);

        if (dias > Integer.MAX_VALUE) {
            return -1;
        }

        return (int) Math.max(0, dias);
    }

    public BigDecimal calcularMonto(int diasRetraso, PoliticaMulta politica){

        if(diasRetraso < 0){
            return null;
        }

        if(diasRetraso == 0){
            return BigDecimal.ZERO;
        }

        if(politica == null || !politica.isActiva()){
            return null;
        }

        if(politica.getDiasGracia() < 0
                || politica.getValorPorDia() == null
                || politica.getValorPorDia().compareTo(BigDecimal.ZERO) < 0
                || (politica.getMultaMaxima() != null
                && politica.getMultaMaxima().compareTo(BigDecimal.ZERO) < 0)){
            return null;
        }

        return calculadoraMulta.calcular(politica, diasRetraso);
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
