
package acceso_datos;

import conexion.ConexionBD;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.PoliticaMulta;

public class PoliticaMultaDAO {
    
    public PoliticaMulta buscarActiva(){
        
        String sql = "SELECT * FROM politica_multa WHERE activa = TRUE";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                long id = resultado.getLong("id_politica");
                String nombre = resultado.getString("nombre");
                BigDecimal valorDia = resultado.getBigDecimal("valor_por_dia");
                int diasGracia = resultado.getInt("dias_gracia");
                BigDecimal multaMax = resultado.getBigDecimal("multa_maxima");
                boolean activa = resultado.getBoolean("activa");
                
                PoliticaMulta consulta = new PoliticaMulta(nombre, valorDia, multaMax);
                consulta.setIdPolitica(id);
                consulta.setDiasGracia(diasGracia);
                consulta.setActiva(activa);
                
                return consulta;
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al buscar por activa la politica multa: "+e.getMessage());
            return null;
        }
    }
    
    public boolean eliminar(long idPolitica){
        
        String sql = "DELETE FROM politica_multa WHERE id_politica = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idPolitica);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al eliminar la politica de multa"+e.getMessage());
            return false;
        }
        
    }
    
    public boolean actualizar(PoliticaMulta politica){
        
        String sql = "UPDATE politica_multa SET nombre = ?, dias_gracia = ?, valor_por_dia = ?, multa_maxima = ?, activa = ? WHERE id_politica = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, politica.getNombre());
            sentencia.setInt(2, politica.getDiasGracia());
            sentencia.setBigDecimal(3, politica.getValorPorDia());
            sentencia.setBigDecimal(4, politica.getMultaMaxima());
            sentencia.setBoolean(5, politica.isActiva());
            sentencia.setLong(6, politica.getIdPolitica());
            
            return sentencia.executeUpdate()> 0;
            
        }catch(SQLException e){
            System.out.println("Error al actualizar la politica de multa: "+e.getMessage());
            return false;
        }
    }
    
    public ArrayList<PoliticaMulta> listarTodos(){
        
        String sql = "SELECT * FROM politica_multa";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<PoliticaMulta> politicasM = new ArrayList<>();
            
            while(resultado.next()){
                long id = resultado.getLong("id_politica");
                String nombre = resultado.getString("nombre");
                BigDecimal valorDia = resultado.getBigDecimal("valor_por_dia");
                int diasGracia = resultado.getInt("dias_gracia");
                BigDecimal multaMax = resultado.getBigDecimal("multa_maxima");
                boolean activa = resultado.getBoolean("activa");
                
                PoliticaMulta consulta = new PoliticaMulta(nombre, valorDia, multaMax);
                consulta.setIdPolitica(id);
                consulta.setDiasGracia(diasGracia);
                consulta.setActiva(activa);
                
                politicasM.add(consulta);
            }
            
            return politicasM;
            
        }catch(SQLException e){
            System.out.println("Error al listar politica multa: "+e.getMessage());
            return null;
        }
        
    }
    
    public PoliticaMulta buscarPorId(long idPolitica){
        
        String sql = "SELECT * FROM politica_multa WHERE id_politica = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return null;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idPolitica);
            
            ResultSet resultado = sentencia.executeQuery();
            
            if(resultado.next()){
                long id = resultado.getLong("id_politica");
                String nombre = resultado.getString("nombre");
                BigDecimal valorDia = resultado.getBigDecimal("valor_por_dia");
                int diasGracia = resultado.getInt("dias_gracia");
                BigDecimal multaMax = resultado.getBigDecimal("multa_maxima");
                boolean activa = resultado.getBoolean("activa");
                
                PoliticaMulta consulta = new PoliticaMulta(nombre, valorDia, multaMax);
                consulta.setIdPolitica(id);
                consulta.setDiasGracia(diasGracia);
                consulta.setActiva(activa);
                
                return consulta;
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al buscar por id la politica multa: "+e.getMessage());
            return null;
        }
    }
    
    public boolean registrar(PoliticaMulta politicaMulta){
        
        String sql = "INSERT INTO politica_multa (nombre, dias_gracia, valor_por_dia, multa_maxima) VALUES (?, ?, ?, ?)";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setString(1, politicaMulta.getNombre());
            sentencia.setInt(2, politicaMulta.getDiasGracia());
            sentencia.setBigDecimal(3, politicaMulta.getValorPorDia());
            sentencia.setBigDecimal(4, politicaMulta.getMultaMaxima());
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al registrar datos en politica multa: "+e.getMessage());
            return false;
        }
    }
    
}
