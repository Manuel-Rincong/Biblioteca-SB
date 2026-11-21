
package servicio;

import java.math.BigDecimal;
import modelo.PoliticaMulta;

public class CalculadoraMulta {

    private MultaStrategy estrategia;

    public CalculadoraMulta(MultaStrategy estrategia){
        if(estrategia == null){
            throw new IllegalArgumentException("La estrategia no puede ser null");
        }

        this.estrategia = estrategia;
    }
    
    public BigDecimal calcular(PoliticaMulta politica, int diasRetraso){
        return estrategia.calcular(politica, diasRetraso);
    }
}
