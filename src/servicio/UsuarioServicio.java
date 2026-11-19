
package servicio;

import acceso_datos.UsuarioDAO;
import java.util.ArrayList;
import modelo.Usuario;

public class UsuarioServicio {
    private UsuarioDAO usuarioDAO;
    
    public UsuarioServicio(){
        this.usuarioDAO = new UsuarioDAO();
    }
    
    
    public boolean registrar(Usuario usuario){
        if(usuario == null){
            return false;
        }
        
        if(usuario.getIdentificacion() == null || usuario.getIdentificacion().trim().isEmpty()){
            return false;
        }
        
        if(usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()){
            return false;
        }
        
        if(usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()){
            return false;
        }
        
        if(usuario.getIdTipoUsuario() <= 0){
            return false;
        }
        
        return usuarioDAO.registrar(usuario);
    }
    
    public Usuario buscarPorId(long idUsuario){
        if(idUsuario <= 0){
            return null;
        }
        
        return usuarioDAO.buscarPorId(idUsuario);
    }
    
    public ArrayList<Usuario> listarTodos(){
        return usuarioDAO.listarTodos();
    }
    
    public boolean actualizar(Usuario usuario){
        if(usuario == null){
            return false;
        }
        
        if(usuario.getIdUsuario() <= 0){
            return false;
        }
        
        if(usuario.getIdentificacion() == null || usuario.getIdentificacion().trim().isEmpty()){
            return false;
        }
        
        if(usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()){
            return false;
        }
        
        if(usuario.getCorreo() == null || usuario.getCorreo().trim().isEmpty()){
            return false;
        }
        
        if(usuario.getIdTipoUsuario() <= 0){
            return false;
        }
        
        return usuarioDAO.actualizar(usuario);
    }
    
    public boolean desactivar (long idUsuario){
        if(idUsuario <= 0){
            return false;
        }
        
        return usuarioDAO.desactivar(idUsuario);
    }
    
}
