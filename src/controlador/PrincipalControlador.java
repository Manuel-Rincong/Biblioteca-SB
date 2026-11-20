
package controlador;

import view.Principal;
import view.Usuarios;
import view.Recursos;

public class PrincipalControlador {
    private Usuarios usuarios;
    private Recursos recursos;
    
    public PrincipalControlador(Principal principal) {
        principal.configurarAccionUsuarios(e -> abrirUsuarios());
        principal.configurarAccionRecursos(e -> abrirRecursos());
    }

    private void abrirUsuarios() {
        if (usuarios == null || !usuarios.isDisplayable()) {
            usuarios = new Usuarios();
        }
        usuarios.setVisible(true);
        usuarios.toFront();
    }  
    
    private void abrirRecursos() {
    if (recursos == null || !recursos.isDisplayable()) {
        recursos = new Recursos();
    }

    recursos.setVisible(true);
    recursos.toFront();
    }
}
