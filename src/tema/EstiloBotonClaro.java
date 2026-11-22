package tema;

import java.awt.Color;
import javax.swing.JButton;

public class EstiloBotonClaro implements EstiloBoton {

    @Override
    public void aplicar(JButton boton) {
        Object fondo = boton.getClientProperty("tema.fondoOriginal");
        Object texto = boton.getClientProperty("tema.textoOriginal");

        if (fondo instanceof Color) {
            boton.setBackground((Color) fondo);
        }

        if (texto instanceof Color) {
            boton.setForeground((Color) texto);
        }
    }
}