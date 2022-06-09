package by.tolkach.classifier.service.rest.object;

import by.tolkach.classifier.dto.serializer.LongLocalDateTimeDeserializer;
import by.tolkach.classifier.dto.serializer.LongLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public abstract class EssenceRestObject {

    private UUID uuid;
    @JsonSerialize(using = LongLocalDateTimeSerializer.class)
    @JsonDeserialize(using = LongLocalDateTimeDeserializer.class)
    private LocalDateTime dtCreate;
    @JsonSerialize(using = LongLocalDateTimeSerializer.class)
    @JsonDeserialize(using = LongLocalDateTimeDeserializer.class)
    private LocalDateTime dtUpdate;

    protected EssenceRestObject() {
    }

    protected EssenceRestObject(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }
}
