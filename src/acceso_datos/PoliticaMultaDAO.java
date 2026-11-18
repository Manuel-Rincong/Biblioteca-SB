
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
    
    private PoliticaMulta convertirPoliticaMulta(ResultSet resultado) throws SQLException {
        long id = resultado.getLong("id_politica");
        String nombre = resultado.getString("nombre");
        BigDecimal valorDia = resultado.getBigDecimal("valor_por_dia");
        int diasGracia = resultado.getInt("dias_gracia");
        BigDecimal multaMax = resultado.getBigDecimal("multa_maxima");
        boolean activa = resultado.getBoolean("activa");

        PoliticaMulta politica = new PoliticaMulta(nombre, valorDia, multaMax);
        politica.setIdPolitica(id);
        politica.setDiasGracia(diasGracia);
        politica.setActiva(activa);

        return politica;
    }
    
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
                return convertirPoliticaMulta(resultado); 
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al buscar la politica de multa por activa: "+e.getMessage());
            return null;
        }
    }
    
    public boolean desactivar(long idPolitica){
        
        String sql = "UPDATE politica_multa SET activa = FALSE WHERE id_politica = ?";
        
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion == null){
            return false;
        }
        
        try(conexion;
            PreparedStatement sentencia = conexion.prepareStatement(sql)){
            
            sentencia.setLong(1, idPolitica);
            
            return sentencia.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.out.println("Error al desactivar la politica de multa: "+e.getMessage());
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
                PoliticaMulta politica = convertirPoliticaMulta(resultado);
                politicasM.add(politica);
            }
            
            return politicasM;
            
        }catch(SQLException e){
            System.out.println("Error al listar todas las politicas de multa: "+e.getMessage());
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
                return convertirPoliticaMulta(resultado); 
            }
            
            return null;
            
        }catch(SQLException e){
            System.out.println("Error al buscar las politicas de multa por el id: "+e.getMessage());
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
            System.out.println("Error al registrar los datos en politica multa: "+e.getMessage());
            return false;
        }
    }
    
}
