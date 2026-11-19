
package servicio;

import acceso_datos.TipoUsuarioDAO;
import modelo.TipoUsuario;
import java.util.ArrayList;

public class TipoUsuarioServicio {

    private TipoUsuarioDAO tipoUsuarioDAO;

    public TipoUsuarioServicio() {
        this.tipoUsuarioDAO = new TipoUsuarioDAO();
    }
    
    public boolean registrar(TipoUsuario tipoUsuario) {

        if (tipoUsuario == null) {
            return false;
        }

        if (tipoUsuario.getNombre() == null || tipoUsuario.getNombre().trim().isEmpty()) {
            return false;
        }

        if (tipoUsuario.getLimitePrestamos() <= 0) {
            return false;
        }

        return tipoUsuarioDAO.registrar(tipoUsuario);
    }
    
    public TipoUsuario buscarPorId(long idTipoUsuario) {

        if (idTipoUsuario <= 0) {
            return null;
        }

        return tipoUsuarioDAO.buscarPorId(idTipoUsuario);
    }
    
    public ArrayList<TipoUsuario> listarTodos(){
        return tipoUsuarioDAO.listarTodos();
    }
    
    public boolean actualizar(TipoUsuario tipoUsuario){
        if(tipoUsuario == null){
            return false;
        }
        
        if(tipoUsuario.getIdTipoUsuario() <= 0){
            return false;
        }
        
        if(tipoUsuario.getNombre() == null || tipoUsuario.getNombre().trim().isEmpty()){
            return false;
        }
        
        if(tipoUsuario.getLimitePrestamos() <= 0){
            return false;
        }
        
        return tipoUsuarioDAO.actualizar(tipoUsuario);
    }
    
    public boolean desactivar(long idTipoUsuario){
        if(idTipoUsuario <= 0){
            return false;
        }
        
        return tipoUsuarioDAO.desactivar(idTipoUsuario);
    }
}
