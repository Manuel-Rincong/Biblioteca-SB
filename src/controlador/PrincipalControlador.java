
package controlador;

import view.Principal;
import view.Usuarios;
import view.Recursos;
import view.Prestamos;
import view.Devoluciones;
import view.Renovaciones;
import view.ListaDeEspera;

public class PrincipalControlador {
    private Usuarios usuarios;
    private Recursos recursos;
    private Prestamos prestamos;
    private Devoluciones devoluciones;
    private Renovaciones renovaciones;
    private ListaDeEspera ListaDeEspera;
    
    public PrincipalControlador(Principal principal) {
        principal.configurarAccionUsuarios(e -> abrirUsuarios());
        principal.configurarAccionRecursos(e -> abrirRecursos());
        principal.configurarAccionPrestamos(e -> abrirPrestamos());
        principal.configurarAccionDevoluciones(e -> abrirDevoluciones());
        principal.configurarAccionRenovaciones(e -> abrirRenovaciones());
        principal.configurarAccionListaDeEspera(e -> abrirListaDeEspera());
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
    
    private void abrirDevoluciones() {
    if (devoluciones == null || !devoluciones.isDisplayable()) {
        devoluciones = new Devoluciones();
    }

    devoluciones.setVisible(true);
    devoluciones.toFront();
    } 
    
    private void abrirRenovaciones() {
    if (renovaciones == null || !renovaciones.isDisplayable()) {
        renovaciones = new Renovaciones();
    }

    renovaciones.setVisible(true);
    renovaciones.toFront();
    } 
    
    private void abrirListaDeEspera() {
    if (ListaDeEspera == null || !ListaDeEspera.isDisplayable()) {
        ListaDeEspera = new ListaDeEspera();
    }

    ListaDeEspera.setVisible(true);
    ListaDeEspera.toFront();
    } 
    
}
