package br.com.zup.proposta.proposta.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class Encryptor {

    @Autowired
    private Environment environment;
    private String myKey;
    private String salt;
    private SecretKey secretKey;
    private IvParameterSpec ivParameterSpec;
    private final String ALGORITHM = "AES/CBC/PKCS5Padding";

    public void init() {
        myKey = environment.getProperty("encryptor.secret.key");
        salt = environment.getProperty("encryptor.secret.salt");
        ivParameterSpec = generateIv();
        secretKey = getKeyFromPassword();
    }

    private IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    private SecretKey getKeyFromPassword() {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(myKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
            return secret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public String encrypt(String toEncrypt) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            byte[] cipherText = cipher.doFinal(toEncrypt.getBytes());
            return Base64.getEncoder().encodeToString(cipherText);
        } catch (Exception e) {
            System.out.println("Erro ao encriptar " + e.toString());
            return null;
        }
    }

    public String decrypt(String toDecrypt) {
        try{
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(toDecrypt));
            return new String(plainText);
        } catch (Exception e) {
            System.out.println("Erro ao decriptar " + e.toString());
            return null;
        }
    }

}
