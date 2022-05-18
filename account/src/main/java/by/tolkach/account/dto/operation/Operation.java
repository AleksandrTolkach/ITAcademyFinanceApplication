package by.tolkach.account.dto.operation;

import by.tolkach.account.dto.Essence;
import by.tolkach.account.dto.serializer.LongLocalDateSerializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Operation extends Essence {

    @JsonSerialize(using = LongLocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    private String description;
    private UUID category;
    private long value;
    private OperationType type;
    private UUID currency;
    private UUID account;

    public Operation() {
    }

    public Operation(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, LocalDate date, String description,
                     UUID category, long value, OperationType type, UUID currency, UUID account) {
        super(uuid, dtCreate, dtUpdate);
        this.date = date;
        this.description = description;
        this.category = category;
        this.value = value;
        this.type = type;
        this.currency = currency;
        this.account = account;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
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

    public void setAccount(UUID account) {
        this.account = account;
    }

    public static class Builder {

        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private LocalDate date;
        private String description;
        private UUID category;
        private long value;
        private OperationType type;
        private UUID currency;
        private UUID account;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setDate(LocalDate date) {
            this.date = date;
            return this;
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

        public Builder setCategory(UUID category) {
            this.category = category;
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

        public Builder setAccount(UUID account) {
            this.account = account;
            return this;
        }

        public Operation build() {
            return new Operation(uuid, dtCreate, dtUpdate, date, description, category, value, type, currency, account);
        }
    }
}
