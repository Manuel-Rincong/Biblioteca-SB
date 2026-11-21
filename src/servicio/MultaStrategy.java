
package servicio;

import java.math.BigDecimal;
import modelo.PoliticaMulta;

public interface MultaStrategy {
    
    BigDecimal calcular(PoliticaMulta politica, int diasRetraso);
}
