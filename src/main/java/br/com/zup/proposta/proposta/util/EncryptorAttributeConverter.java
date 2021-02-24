package br.com.zup.proposta.proposta.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;

@Component
public class EncryptorAttributeConverter implements AttributeConverter<String, String> {

    @Autowired
    private Encryptor encryptor;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return encryptor.encode(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return encryptor.decode(dbData);
    }

}
