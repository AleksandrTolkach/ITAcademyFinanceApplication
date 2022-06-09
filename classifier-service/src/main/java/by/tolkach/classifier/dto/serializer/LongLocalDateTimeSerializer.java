package by.tolkach.classifier.dto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class LongLocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    public LongLocalDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        ZonedDateTime zonedDateTime = value.atZone(ZoneId.systemDefault());
        gen.writeNumber(zonedDateTime.toInstant().toEpochMilli());
    }
}
