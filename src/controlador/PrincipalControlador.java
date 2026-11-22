
package controlador;

import view.Principal;
import view.Usuarios;
import view.Recursos;
import view.Prestamos;
import view.Devoluciones;
import view.Renovaciones;
import view.ListaDeEspera;
import view.Notificaciones;
import view.Consultas;
import tema.GestorTema;

public class PrincipalControlador {
    private Usuarios usuarios;
    private final GestorTema gestorTema = new GestorTema();
    private Recursos recursos;
    private Prestamos prestamos;
    private Devoluciones devoluciones;
    private Renovaciones renovaciones;
    private ListaDeEspera ListaDeEspera;
    private Notificaciones Notificaciones;
    private Consultas Consultas;
    
    public PrincipalControlador(Principal principal) {
        principal.configurarAccionUsuarios(e -> abrirUsuarios());
        principal.configurarAccionRecursos(e -> abrirRecursos());
        principal.configurarAccionPrestamos(e -> abrirPrestamos());
        principal.configurarAccionDevoluciones(e -> abrirDevoluciones());
        principal.configurarAccionRenovaciones(e -> abrirRenovaciones());
        principal.configurarAccionListaDeEspera(e -> abrirListaDeEspera());
        principal.configurarAccionNotificaciones(e -> abrirNotificaciones());
        principal.configurarAccionConsultas(e -> abrirConsultas());
        principal.configurarAccionTema(e -> {
    gestorTema.alternarTema();
    principal.actualizarTextoTema(gestorTema.esOscuro());
});

gestorTema.aplicar(principal);
principal.actualizarTextoTema(gestorTema.esOscuro());
    }

    private void abrirUsuarios() {
        if (usuarios == null || !usuarios.isDisplayable()) {
            usuarios = new Usuarios();
            new UsuariosControlador(usuarios);
        }
        usuarios.setVisible(true);
        usuarios.toFront();
    }  
    
    private void abrirRecursos() {
    if (recursos == null || !recursos.isDisplayable()) {
        recursos = new Recursos();
        new RecursosControlador(recursos);
    }

    recursos.setVisible(true);
    recursos.toFront();
    }
    
    private void abrirPrestamos() {
    if (prestamos == null || !prestamos.isDisplayable()) {
        prestamos = new Prestamos();
        new PrestamosControlador(prestamos);
    }

    prestamos.setVisible(true);
    prestamos.toFront();
    }
    
    private void abrirDevoluciones() {
    if (devoluciones == null || !devoluciones.isDisplayable()) {
        devoluciones = new Devoluciones();
        new DevolucionesControlador(devoluciones);
    }

    devoluciones.setVisible(true);
    devoluciones.toFront();
    } 
    
    private void abrirRenovaciones() {
    if (renovaciones == null || !renovaciones.isDisplayable()) {
        renovaciones = new Renovaciones();
        new RenovacionesControlador(renovaciones);
    }

    renovaciones.setVisible(true);
    renovaciones.toFront();
    } 
    
    private void abrirListaDeEspera() {
    if (ListaDeEspera == null || !ListaDeEspera.isDisplayable()) {
        ListaDeEspera = new ListaDeEspera();
        new ListaDeEsperaControlador(ListaDeEspera);
    }

    ListaDeEspera.setVisible(true);
    ListaDeEspera.toFront();
    } 
    
    private void abrirNotificaciones() {
    if (Notificaciones == null || !Notificaciones.isDisplayable()) {
        Notificaciones = new Notificaciones();
        new NotificacionesControlador(Notificaciones);
    }

    Notificaciones.setVisible(true);
    Notificaciones.toFront();
    } 
    
    private void abrirConsultas () {
    if (Consultas == null || !Consultas.isDisplayable()) {
        Consultas = new Consultas();
        new ConsultasControlador(Consultas);
    }

    Consultas.setVisible(true);
    Consultas.toFront();
    } 
    
}
