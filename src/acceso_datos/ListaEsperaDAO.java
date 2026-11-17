
package acceso_datos;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelo.ListaEspera;

public class ListaEsperaDAO {
    
    private ListaEspera convertirListaEspera(ResultSet resultado) throws SQLException{
        long id = resultado.getLong("id_lista_espera");
        long idUsuario = resultado.getLong("id_usuario");
        long idRecursoConsulta = resultado.getLong("id_recurso");
        LocalDateTime fechaInscripcion = resultado.getTimestamp("fecha_inscripcion").toLocalDateTime();
        String estado = resultado.getString("estado");
        LocalDateTime fechaAviso = null;
        java.sql.Timestamp fechaAvisoSQL = resultado.getTimestamp("fecha_aviso");

        if (fechaAvisoSQL != null) {
            fechaAviso = fechaAvisoSQL.toLocalDateTime();
        }

        LocalDateTime fechaVencimientoAviso = null;
        java.sql.Timestamp fechaVencimientoAvisoSQL = resultado.getTimestamp("fecha_vencimiento_aviso");

        if (fechaVencimientoAvisoSQL != null) {
            fechaVencimientoAviso = fechaVencimientoAvisoSQL.toLocalDateTime();
        }
                
        ListaEspera consulta = new ListaEspera(idUsuario, idRecursoConsulta);
        consulta.setIdListaEspera(id);
        consulta.setFechaInscripcion(fechaInscripcion);
        consulta.setEstado(estado);
        consulta.setFechaAviso(fechaAviso);
        consulta.setFechaVencimientoAviso(fechaVencimientoAviso);
        
        return consulta;
    }
    
    public boolean cancelar(long idListaEspera){
        return actualizarEstado(idListaEspera, "CANCELADO");
    }
    
    public boolean actualizarEstado(long idListaEspera, String estado){
        
        String sql = "UPDATE lista_espera SET estado = ? WHERE id_lista_espera = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, estado);
            sentencia.setLong(2, idListaEspera);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al actualizar el estado de lista de espera: "+e.getMessage());
            return false;
        }
    } 
    
    public ListaEspera buscarSiguienteEnEspera(long idRecurso){
        
        String sql = "SELECT * FROM lista_espera WHERE id_recurso = ? AND estado = 'ESPERA' ORDER BY fecha_inscripcion ASC LIMIT 1";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idRecurso);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                return convertirListaEspera(resultado);
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al buscar el siguiente en lista de espera: "+e.getMessage());
            return null;
        }
    }
    
    public ArrayList<ListaEspera> listarPorRecurso(long idRecurso){
        
        String sql = "SELECT * FROM lista_espera WHERE id_recurso = ? ORDER BY fecha_inscripcion ASC";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idRecurso);
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<ListaEspera> listasEspera = new ArrayList<>();
            
            while(resultado.next()){
                ListaEspera listaEspera = convertirListaEspera(resultado);
                listasEspera.add(listaEspera);
            }
            
            return listasEspera;
            
        }catch(SQLException e){
            System.out.println("Error al buscar lista de espera por recurso: "+e.getMessage());
            return null;
        }
    }
    
    public boolean registrar(ListaEspera listaEspera){
        
        String sql = "INSERT INTO lista_espera (id_usuario, id_recurso) VALUES (?, ?)";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, listaEspera.getIdUsuario());
            sentencia.setLong(2, listaEspera.getIdRecurso());
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al registrar los datos en lista de espera: "+e.getMessage());
            return false;
        }
    }
}
