package by.tolkach.mailScheduler.dao.api.entity.converter.attributesConverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.ByteArrayOutputStream;

@Converter
public class BytesAttributeConverter implements AttributeConverter<ByteArrayOutputStream, byte[]> {
    @Override
    public byte[] convertToDatabaseColumn(ByteArrayOutputStream attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.toByteArray();
    }

    @Override
    public ByteArrayOutputStream convertToEntityAttribute(byte[] dbData) {
        if (dbData == null) {
            return null;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream(dbData.length);
        bos.write(dbData, 0, dbData.length);
        return bos;
    }
}
