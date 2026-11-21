
package servicio;

import java.util.ArrayList;
import java.sql.Connection;

public class DisponibilidadSubject {

    private ArrayList<DisponibilidadObserver> observadores;

    public DisponibilidadSubject(){
        this.observadores = new ArrayList<>();
    }
    
    public void agregarObservador(DisponibilidadObserver observador){
        if(observador != null && !observadores.contains(observador)){
            observadores.add(observador);
        }
    }
    
    public void eliminarObservador(DisponibilidadObserver observador){
        if(observador != null){
            observadores.remove(observador);
        }
    }
    
    public boolean notificarObservadores(long idRecurso, long idUsuario, long idListaEspera, Connection conexion){

        if(idRecurso <= 0 || idUsuario <= 0 || idListaEspera <= 0 || conexion == null){
            return false;
        }

        for(DisponibilidadObserver observador : observadores){
            if(!observador.actualizar(idRecurso, idUsuario, idListaEspera, conexion)){
                return false;
            }
        }

        return true;
    }
}