
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static ConexionBD instancia;

    private final String URL = "jdbc:postgresql://aws-0-us-east-2.pooler.supabase.com:5432/postgres?user=postgres.affobcpntqbxcxxodvtn&password=--2Biblioteca2--";
    private final String USUARIO = "postgres.affobcpntqbxcxxodvtn";
    private final String PASSWORD = "--2Biblioteca2--";

    private ConexionBD() {
    }

    public static synchronized ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }

        return instancia;
    }

    public Connection obtenerConexion() {
        try {
            return DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}