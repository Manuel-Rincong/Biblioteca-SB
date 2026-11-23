package servicio;

import java.math.BigDecimal;
import modelo.PoliticaMulta;

public class MultaFija implements MultaStrategy {

    @Override
    public BigDecimal calcular(PoliticaMulta politica, int diasRetraso) {

        if (politica == null || diasRetraso < 0) {
            return null;
        }

        if (politica.getDiasGracia() < 0
                || politica.getValorPorDia() == null
                || politica.getValorPorDia().compareTo(BigDecimal.ZERO) < 0
                || (politica.getMultaMaxima() != null
                && politica.getMultaMaxima().compareTo(BigDecimal.ZERO) < 0)) {
            return null;
        }

        int retraso = diasRetraso - politica.getDiasGracia();

        if (retraso <= 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal monto = politica.getValorPorDia();

        if (politica.getMultaMaxima() != null
                && monto.compareTo(politica.getMultaMaxima()) > 0) {
            return politica.getMultaMaxima();
        }

        return monto;
    }
}