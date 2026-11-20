
package controlador;

import view.Principal;
import view.Usuarios;
import view.Recursos;
import view.Prestamos;

public class PrincipalControlador {
    private Usuarios usuarios;
    private Recursos recursos;
    private Prestamos prestamos;
    
    public PrincipalControlador(Principal principal) {
        principal.configurarAccionUsuarios(e -> abrirUsuarios());
        principal.configurarAccionRecursos(e -> abrirRecursos());
        principal.configurarAccionPrestamos(e -> abrirPrestamos());
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
    
    private void abrirPrestamos() {
    if (prestamos == null || !prestamos.isDisplayable()) {
        prestamos = new Prestamos();
    }

    prestamos.setVisible(true);
    prestamos.toFront();
    }
    
}
