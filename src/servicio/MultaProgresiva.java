package servicio;

import java.math.BigDecimal;
import modelo.PoliticaMulta;

public class MultaProgresiva implements MultaStrategy {

    @Override
    public BigDecimal calcular(PoliticaMulta politica, int diasRetraso) {

        if (politica == null || diasRetraso < 0) {
            return null;
        }

        if (politica.getDiasGracia() < 0
                || politica.getMultaMaxima() != null
                && politica.getMultaMaxima().compareTo(BigDecimal.ZERO) < 0) {
            return null;
        }

        int retraso = diasRetraso - politica.getDiasGracia();

        if (retraso <= 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal monto = BigDecimal.ZERO;

        int diasPrimerTramo = Math.min(retraso, 2);
        int diasSegundoTramo = Math.min(Math.max(retraso - 2, 0), 3);
        int diasTercerTramo = Math.max(retraso - 5, 0);

        monto = monto.add(BigDecimal.valueOf(diasPrimerTramo).multiply(BigDecimal.valueOf(1000)));

        monto = monto.add(BigDecimal.valueOf(diasSegundoTramo).multiply(BigDecimal.valueOf(2000)));

        monto = monto.add(BigDecimal.valueOf(diasTercerTramo).multiply(BigDecimal.valueOf(3000)));

        if (politica.getMultaMaxima() != null
                && monto.compareTo(politica.getMultaMaxima()) > 0) {
            return politica.getMultaMaxima();
        }

        return monto;
    }
}