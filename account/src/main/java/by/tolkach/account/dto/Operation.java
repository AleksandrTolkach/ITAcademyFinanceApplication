package by.tolkach.account.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonIgnoreProperties({"type"})
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Operation extends Essence {

    private String description;
    private long value;
    private OperationType type;
    private UUID currency;

    public Operation() {
    }

    public Operation(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String description, long value,
                     OperationType type, UUID currency) {
        super(uuid, dtCreate, dtUpdate);
        this.description = description;
        this.value = value;
        this.type = type;
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public UUID getCurrency() {
        return currency;
    }

    public void setCurrency(UUID currency) {
        this.currency = currency;
    }

    public static class Builder {

        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private String description;
        private long value;
        private OperationType type;
        private UUID currency;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setDtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public Builder setDtUpdate(LocalDateTime dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setValue(long value) {
            this.value = value;
            return this;
        }

        public Builder setType(OperationType type) {
            this.type = type;
            return this;
        }

        public Builder setCurrency(UUID currency) {
            this.currency = currency;
            return this;
        }

        public Operation build() {
            return new Operation(uuid, dtCreate, dtUpdate, description, value, type, currency);
        }
    }
}
