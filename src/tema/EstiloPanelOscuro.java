package tema;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public class EstiloPanelOscuro implements EstiloPanel {

    @Override
    public void aplicar(JPanel panel) {
        aplicarComponente(panel);
    }

    private void aplicarComponente(Component componente) {
        if (componente instanceof JComponent && !(componente instanceof JButton)) {
            JComponent elemento = (JComponent) componente;

            if (elemento.getClientProperty("tema.fondoOriginal") == null) {
                elemento.putClientProperty("tema.fondoOriginal", elemento.getBackground());
                elemento.putClientProperty("tema.textoOriginal", elemento.getForeground());
            }

            elemento.setBackground(new Color(32, 34, 37));
            elemento.setForeground(new Color(235, 235, 235));

            if (elemento instanceof JTextComponent) {
                JTextComponent campo = (JTextComponent) elemento;

                if (campo.getClientProperty("tema.cursorOriginal") == null) {
                    campo.putClientProperty("tema.cursorOriginal", campo.getCaretColor());
                    campo.putClientProperty("tema.cursorGuardado", Boolean.TRUE);
                }

                campo.setCaretColor(new Color(235, 235, 235));
            }
        }

        if (componente instanceof Container) {
            Container contenedor = (Container) componente;

            for (Component hijo : contenedor.getComponents()) {
                aplicarComponente(hijo);
            }
        }
    }
}