package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "URL";
    private static final String USUARIO = "USUARIO";
    private static final String CONTRASENA = "CONTRASENA";

    public static Connection obtenerConexion() {

        try {
            Connection conexion = DriverManager.getConnection(
                URL,
                USUARIO,
                CONTRASENA
            );

            System.out.println("Conexion exitosa con Supabase");

            return conexion;

        } catch (SQLException e) {

            System.out.println("Error al conectar con la base de datos:");
            System.out.println(e.getMessage());

            return null;
        }
    }
}