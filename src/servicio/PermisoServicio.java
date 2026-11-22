
package servicio;

import modelo.SesionUsuario;

public class PermisoServicio {
    
    public boolean esAdministrador(SesionUsuario sesion) {
        if (sesion == null) {
            return false;
        }

        return "Administrador".equals(sesion.getTipoUsuario());
    }
    
    public boolean esEstudiante(SesionUsuario sesion) {
        if (sesion == null) {
            return false;
        }

        return "Estudiante".equals(sesion.getTipoUsuario());
    }
    
    public boolean esProfesor(SesionUsuario sesion) {
        if (sesion == null) {
            return false;
        }

        return "Profesor".equals(sesion.getTipoUsuario());
    }
    
    public boolean puedeGestionarUsuarios(SesionUsuario sesion) {
        return esAdministrador(sesion);
    }
    
    public boolean puedeGestionarRecursos(SesionUsuario sesion) {
        return esAdministrador(sesion);
    }
    
    public boolean puedeGestionarPrestamos(SesionUsuario sesion) {
        if (sesion == null) {
            return false;
        }

        return esAdministrador(sesion)
                || esEstudiante(sesion)
                || esProfesor(sesion);
    }
    
    public boolean puedeConsultarReportes(SesionUsuario sesion) {
        return esAdministrador(sesion);
    }
    
    public boolean puedeConsultarCatalogo(SesionUsuario sesion) {
        if (sesion == null) {
            return false;
        }

        return esAdministrador(sesion)
                || esEstudiante(sesion)
                || esProfesor(sesion);
    }
    
    public boolean puedeConsultarDisponibilidad(SesionUsuario sesion) {
        return puedeConsultarCatalogo(sesion);
    }
    
    public boolean puedeGestionarListaEspera(SesionUsuario sesion) {
        return puedeConsultarCatalogo(sesion);
    }
    
    public boolean puedeConsultarPrestamos(SesionUsuario sesion) {
        return puedeGestionarPrestamos(sesion);
    }
    
    public boolean puedeConsultarPrestamosDeOtros(SesionUsuario sesion) {
        return esAdministrador(sesion);
    }
    
    public boolean puedeGestionarMultas(SesionUsuario sesion) {
        return esAdministrador(sesion);
    }
}
