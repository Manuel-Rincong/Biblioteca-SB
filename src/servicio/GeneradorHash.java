
package servicio;

import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;
import java.security.MessageDigest;

public class GeneradorHash {
    
    public String generarHash(String contrasena) {
        if (contrasena == null || contrasena.isEmpty()) {
            return null;
        }

        try {
            int iteraciones = 600000;
            int longitudHash = 256;

            byte[] sal = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(sal);

            PBEKeySpec especificacion = new PBEKeySpec(contrasena.toCharArray(), sal, iteraciones, longitudHash);

            SecretKeyFactory fabrica = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hash = fabrica.generateSecret(especificacion).getEncoded();

            return iteraciones + ":"
                    + Base64.getEncoder().encodeToString(sal) + ":"
                    + Base64.getEncoder().encodeToString(hash);

        } catch (Exception e) {
            System.out.println("Error al generar el hash: " + e.getMessage());
            return null;
        }
    }
    
    public boolean verificarContrasena(String contrasena, String hashAlmacenado) {

        if (contrasena == null || contrasena.isEmpty()
                || hashAlmacenado == null || hashAlmacenado.isEmpty()) {
            return false;
        }

        PBEKeySpec especificacion = null;

        try {
            String[] partes = hashAlmacenado.split(":");

            if (partes.length != 3) {
                return false;
            }

            int iteraciones = Integer.parseInt(partes[0]);

            if (iteraciones < 1 || iteraciones > 2000000) {
                return false;
            }

            byte[] sal = Base64.getDecoder().decode(partes[1]);
            byte[] hashOriginal = Base64.getDecoder().decode(partes[2]);

            if (sal.length != 16 || hashOriginal.length != 32) {
                return false;
            }

            especificacion = new PBEKeySpec(
                    contrasena.toCharArray(),
                    sal,
                    iteraciones,
                    256
            );

            SecretKeyFactory fabrica = SecretKeyFactory.getInstance(
                    "PBKDF2WithHmacSHA256"
            );

            byte[] hashCalculado = fabrica.generateSecret(especificacion).getEncoded();

            return MessageDigest.isEqual(hashOriginal, hashCalculado);

        } catch (Exception e) {
            System.out.println("Error al verificar la contraseña: " + e.getMessage());
            return false;

        } finally {
            if (especificacion != null) {
                especificacion.clearPassword();
            }
        }
    }
}
