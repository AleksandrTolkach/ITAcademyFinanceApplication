package by.tolkach.classifier.dto.serializer;

import by.tolkach.classifier.dto.exception.NotFoundException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LongLocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    public LongLocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        long value = p.getValueAsLong();
        if (value == 0) {
            throw new NotFoundException("Неверный тип данных - " + value);
        }
        return Instant.ofEpochMilli(p.getValueAsLong())
                .atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
