
package servicio;

import java.sql.Connection;

public interface DisponibilidadObserver {
    
    public boolean actualizar(long idRecurso, long idUsuario, long idListaEspera, Connection conexion);
}
