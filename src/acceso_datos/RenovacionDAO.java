
package acceso_datos;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelo.Renovacion;

public class RenovacionDAO {
    
    private Renovacion convertirRenovacion(ResultSet resultado) throws SQLException {
        long id = resultado.getLong("id_renovacion");
        long idPrestamo = resultado.getLong("id_prestamo");

        LocalDate fechaLimiteAnterior = resultado.getDate("fecha_limite_anterior").toLocalDate();
        LocalDate nuevaFechaLimite = resultado.getDate("nueva_fecha_limite").toLocalDate();
        LocalDateTime fechaRenovacion = resultado.getTimestamp("fecha_renovacion").toLocalDateTime();

        Renovacion renovacion = new Renovacion(idPrestamo, fechaLimiteAnterior, nuevaFechaLimite);

        renovacion.setIdRenovacion(id);
        renovacion.setFechaRenovacion(fechaRenovacion);

        return renovacion;
    }
    
    public int contarPorPrestamo(long idPrestamo){
        
        String sql = "SELECT COUNT(*) FROM renovacion WHERE id_prestamo = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return (-1);
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idPrestamo);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                return resultado.getInt("count");
            }
            
            return 0;
            
        }catch(SQLException e){
            System.out.println("Error al contar renovaciones por el id del prestamo: "+e.getMessage());
            return (-1);
        }
    }
    
    public ArrayList<Renovacion> listarPorPrestamo(long idPrestamo){
        
        String sql = "SELECT * FROM renovacion WHERE id_prestamo = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idPrestamo);
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<Renovacion> renovaciones = new ArrayList<>();
            
            while(resultado.next()){
                Renovacion renovacion = convertirRenovacion(resultado);
                renovaciones.add(renovacion);
            }
            
            return renovaciones;
            
            
        }catch(SQLException e){
            System.out.println("Error al listar la renovacion por el id del prestamo: "+e.getMessage());
            return null;
        }
    }
    
    public boolean registrar(Renovacion renovacion){
        
        String sql = "INSERT INTO renovacion (id_prestamo, fecha_limite_anterior, nueva_fecha_limite) VALUES (?, ?, ?)";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if(conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, renovacion.getIdPrestamo());
            sentencia.setDate(2, java.sql.Date.valueOf(renovacion.getFechaLimiteAnterior()));
            sentencia.setDate(3, java.sql.Date.valueOf(renovacion.getNuevaFechaLimite()));
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al registrar los datos en renovacion: "+e.getMessage());
            return false;
        }
    }
    
}
