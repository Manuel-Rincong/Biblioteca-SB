
package servicio;

import acceso_datos.CredencialDAO;
import acceso_datos.UsuarioDAO;
import acceso_datos.TipoUsuarioDAO;

import modelo.Credencial;
import modelo.Usuario;
import modelo.SesionUsuario;
import modelo.TipoUsuario;

public class AutenticacionServicio {
    private CredencialDAO credencialDAO;
    private UsuarioDAO usuarioDAO;
    private TipoUsuarioDAO tipoUsuarioDAO;
    private GeneradorHash generadorHash;
    private SesionUsuario sesionActual;
    
    public AutenticacionServicio() {
        this.credencialDAO = new CredencialDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.tipoUsuarioDAO = new TipoUsuarioDAO();
        this.generadorHash = new GeneradorHash();
        this.sesionActual = null;
    }
    
    public boolean registrarCredenciales(long idUsuario, String contrasena){
        if(idUsuario <= 0){
            return false;
        }
        
        if(contrasena == null || contrasena.trim().isEmpty()){
            return false;
        }
        
        if(contrasena.length() < 8){
            return false;
        }
        
        Usuario usuario = usuarioDAO.buscarPorId(idUsuario);

        if(usuario == null || !usuario.isActivo()){
            return false;
        }

        Credencial credencialExistente = credencialDAO.buscarPorUsuario(idUsuario);

        if(credencialExistente != null){
            return false;
        }

        String hash = generadorHash.generarHash(contrasena);

        if(hash == null){
            return false;
        }

        Credencial credencial = new Credencial(idUsuario, hash, true);

        return credencialDAO.registrar(credencial);
    }
    
    public SesionUsuario iniciarSesion(String correo, String contrasena){
        this.sesionActual = null;
        
       if(correo == null || correo.trim().isEmpty()) {
           return null;
       }
       if(contrasena == null || contrasena.trim().isEmpty()){
           return null;
       }
       
       Usuario usuario = usuarioDAO.buscarPorCorreo(correo);

        if(usuario == null || !usuario.isActivo()){
            return null;
        }

        Credencial credencial = credencialDAO.buscarPorUsuario(usuario.getIdUsuario());

        if(credencial == null || !credencial.isActivo()){
            return null;
        }

        boolean contrasenaValida = generadorHash.verificarContrasena(
                contrasena,
                credencial.getContrasenaHash()
        );

        if(!contrasenaValida){
            return null;
        }

        TipoUsuario tipoUsuario = tipoUsuarioDAO.buscarPorId(usuario.getIdTipoUsuario());

        if(tipoUsuario == null || !tipoUsuario.isActivo()){
            return null;
        }

        SesionUsuario sesion = new SesionUsuario(usuario.getIdUsuario(), usuario.getNombre(), usuario.getCorreo(), usuario.getIdTipoUsuario(), tipoUsuario.getNombre());

        this.sesionActual = sesion;

        return sesion;
    }
    
    public void cerrarSesion(){
        this.sesionActual = null;
    }
    
    public SesionUsuario getSesionActual(){
        return sesionActual;
    }
}
