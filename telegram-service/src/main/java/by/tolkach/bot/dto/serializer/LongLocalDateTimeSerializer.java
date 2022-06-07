package by.tolkach.bot.dto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class LongLocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    public LongLocalDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        ZonedDateTime zonedDateTime = value.atZone(ZoneOffset.of("+03:00"));
        gen.writeNumber(zonedDateTime.toInstant().toEpochMilli());
    }
}
