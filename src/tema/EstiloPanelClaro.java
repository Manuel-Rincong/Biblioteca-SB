package tema;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public class EstiloPanelClaro implements EstiloPanel {

    @Override
    public void aplicar(JPanel panel) {
        restaurarComponente(panel);
    }

    private void restaurarComponente(Component componente) {
        if (componente instanceof JComponent && !(componente instanceof JButton)) {
            JComponent elemento = (JComponent) componente;
            Object fondo = elemento.getClientProperty("tema.fondoOriginal");
            Object texto = elemento.getClientProperty("tema.textoOriginal");

            if (fondo instanceof Color) {
                elemento.setBackground((Color) fondo);
            }

            if (texto instanceof Color) {
                elemento.setForeground((Color) texto);
            }

            if (elemento instanceof JTextComponent) {
                JTextComponent campo = (JTextComponent) elemento;

                if (Boolean.TRUE.equals(campo.getClientProperty("tema.cursorGuardado"))) {
                    campo.setCaretColor((Color) campo.getClientProperty("tema.cursorOriginal"));
                }
            }
        }

        if (componente instanceof Container) {
            Container contenedor = (Container) componente;

            for (Component hijo : contenedor.getComponents()) {
                restaurarComponente(hijo);
            }
        }
    }
}
