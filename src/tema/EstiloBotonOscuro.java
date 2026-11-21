package tema;

import java.awt.Color;
import javax.swing.JButton;

public class EstiloBotonOscuro implements EstiloBoton {

    @Override
    public void aplicar(JButton boton) {
        if (boton.getClientProperty("tema.fondoOriginal") == null) {
            boton.putClientProperty("tema.fondoOriginal", boton.getBackground());
            boton.putClientProperty("tema.textoOriginal", boton.getForeground());
        }

        boton.setBackground(new Color(55, 58, 63));
        boton.setForeground(new Color(240, 240, 240));
    }
}
