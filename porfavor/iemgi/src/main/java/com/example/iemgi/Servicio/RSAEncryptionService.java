package com.example.iemgi.Servicio;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

@Service
public class RSAEncryptionService {

    private KeyPair keyPair;

    public RSAEncryptionService() throws Exception {
        // Generar un par de claves RSA
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        keyPair = keyPairGenerator.generateKeyPair();
    }

    public String encriptar(String textoPlano) throws Exception {
        // Inicializar el cifrado con la clave pública
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());

        // Encriptar el texto plano
        byte[] textoEncriptado = cipher.doFinal(textoPlano.getBytes());

        // Codificar el texto encriptado como una cadena Base64
        return Base64.getEncoder().encodeToString(textoEncriptado);
    }

    public String desencriptar(String textoEncriptado) throws Exception {
        // Decodificar el texto encriptado de Base64
        byte[] textoEncriptadoBytes = Base64.getDecoder().decode(textoEncriptado);

        // Inicializar el cifrado con la clave privada
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());

        byte[] textoDesencriptadoBytes = cipher.doFinal(textoEncriptadoBytes);

        return new String(textoDesencriptadoBytes);
    }
}
