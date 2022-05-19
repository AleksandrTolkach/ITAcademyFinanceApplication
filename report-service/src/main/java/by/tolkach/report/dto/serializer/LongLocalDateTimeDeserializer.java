package by.tolkach.report.dto.serializer;

import com.fasterxml.jackson.core.JacksonException;
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
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return Instant.ofEpochMilli(p.getValueAsLong())
                .atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
