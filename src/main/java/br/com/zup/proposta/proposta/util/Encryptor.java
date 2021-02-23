package br.com.zup.proposta.proposta.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;

@Component
public class Encryptor implements AttributeConverter<String, String> {

    @Value("${encryptor.secret.key}")
    private String secret;
    @Value("${encryptor.secret.salt}")
    private String salt;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return Encryptors.text(secret, salt).encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return Encryptors.text(secret, salt).decrypt(dbData);
    }

}
