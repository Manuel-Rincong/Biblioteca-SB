package tema;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public class GestorTema {

    private FabricaTema fabrica = new FabricaTemaClaro();
    private boolean oscuro;

    public boolean esOscuro() {
        return oscuro;
    }

    public void alternarTema() {
        oscuro = !oscuro;

        if (oscuro) {
            fabrica = new FabricaTemaOscuro();
        } else {
            fabrica = new FabricaTemaClaro();
        }

        for (Window ventana : Window.getWindows()) {
            if (ventana instanceof JFrame && ventana.isDisplayable()) {
                aplicar((JFrame) ventana);
            }
        }
    }

    public void aplicar(JFrame ventana) {
        Container contenido = ventana.getContentPane();

        if (contenido instanceof JPanel) {
            aplicar((JPanel) contenido);
        }

        ventana.repaint();
    }

    public void aplicar(JPanel panel) {
        guardarOriginales(panel);
        fabrica.crearEstiloPanel().aplicar(panel);
        aplicarBotones(panel, fabrica.crearEstiloBoton());
        panel.repaint();
    }

    private void guardarOriginales(Component componente) {
        if (componente instanceof JComponent) {
            JComponent elemento = (JComponent) componente;

            if (elemento.getClientProperty("tema.fondoOriginal") == null) {
                elemento.putClientProperty(
                        "tema.fondoOriginal", elemento.getBackground());
                elemento.putClientProperty(
                        "tema.textoOriginal", elemento.getForeground());
            }

            if (elemento instanceof JTextComponent) {
                JTextComponent campo = (JTextComponent) elemento;

                if (!Boolean.TRUE.equals(
                        campo.getClientProperty("tema.cursorGuardado"))) {
                    campo.putClientProperty(
                            "tema.cursorOriginal", campo.getCaretColor());
                    campo.putClientProperty(
                            "tema.cursorGuardado", Boolean.TRUE);
                }
            }
        }

        if (componente instanceof Container) {
            Container contenedor = (Container) componente;

            for (Component hijo : contenedor.getComponents()) {
                guardarOriginales(hijo);
            }
        }
    }

    private void aplicarBotones(Component componente, EstiloBoton estilo) {
        if (componente instanceof JButton) {
            estilo.aplicar((JButton) componente);
        }

        if (componente instanceof Container) {
            Container contenedor = (Container) componente;

            for (Component hijo : contenedor.getComponents()) {
                aplicarBotones(hijo, estilo);
            }
        }
    }
}
