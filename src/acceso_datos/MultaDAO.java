
package acceso_datos;

import conexion.ConexionBD;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelo.Multa;

public class MultaDAO {
    
    private Multa convertirMulta(ResultSet resultado) throws SQLException{
        long id = resultado.getLong("id_multa");
        long idPrestamo = resultado.getLong("id_prestamo");
        long idPolitica = resultado.getLong("id_politica");
        int diasRetraso = resultado.getInt("dias_retraso");
        BigDecimal montoTotal = resultado.getBigDecimal("monto_total");
        String estado = resultado.getString("estado");
        LocalDateTime fechaGeneracion = resultado.getTimestamp("fecha_generacion").toLocalDateTime();
        
        Multa consulta = new Multa(idPrestamo, idPolitica, diasRetraso, montoTotal, estado);
        
        consulta.setIdMulta(id);
        consulta.setFechaGeneracion(fechaGeneracion);
        
        return consulta;
    }
    
    public boolean actualizarEstado(long idMulta, String estado){
        
        String sql = "UPDATE multa SET estado = ? WHERE id_multa = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, estado);
            sentencia.setLong(2, idMulta);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al actualizar el estado de la multa por el id: "+e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Multa> listarPendientesPorUsuario(long idUsuario){
        
        String sql = "SELECT multa.* FROM multa INNER JOIN prestamo ON multa.id_prestamo = prestamo.id_prestamo WHERE prestamo.id_usuario = ? AND multa.estado = 'PENDIENTE'";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idUsuario);
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<Multa> multas = new ArrayList<> ();
            
            while(resultado.next()){
                Multa multa = convertirMulta(resultado);
                multas.add(multa);
            }
            
            return multas;
            
        }catch(SQLException e){
            System.out.println("Error al listar multas pendientes por el id de usuario: "+e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Multa> listarPorUsuario(long idUsuario){
        
        String sql = "SELECT multa.* FROM multa INNER JOIN prestamo ON multa.id_prestamo = prestamo.id_prestamo WHERE prestamo.id_usuario = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idUsuario);
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<Multa> multas = new ArrayList<> ();
            
            while(resultado.next()){
                Multa multa = convertirMulta(resultado);
                multas.add(multa);
            }
            
            return multas;
            
        }catch(SQLException e){
            System.out.println("Error al listar multas por el id de usuario: "+e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Multa> listarPorPrestamo(long idPrestamo){
        
        String sql = "SELECT * FROM multa WHERE id_prestamo = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idPrestamo);
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<Multa> multas = new ArrayList<> ();
            
            while(resultado.next()){
                Multa multa = convertirMulta(resultado);
                multas.add(multa);
            }
            
            return multas;
            
        }catch(SQLException e){
            System.out.println("Error al listar multas por el id de prestamo: "+e.getMessage());
            return null;
        }
    }
    
    public Multa buscarPorId(long idMulta){
        
        String sql = "SELECT * FROM multa WHERE id_multa = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idMulta);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                return convertirMulta(resultado);
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al buscar multas por el id de multa: "+e.getMessage());
            return null;
        }
    }
    
    public boolean registrar(Multa multa){
        
        String sql = "INSERT INTO multa (id_prestamo, id_politica, dias_retraso, monto_total, estado) VALUES (?, ?, ?, ?, ?)";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, multa.getIdPrestamo());
            sentencia.setLong(2,multa.getIdPolitica());
            sentencia.setInt(3, multa.getDiasRetraso());
            sentencia.setBigDecimal(4, multa.getMontoTotal());
            sentencia.setString(5, multa.getEstado());
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al registrar los datos en multa: "+e.getMessage());
            return false;
        }
    }
    
}
