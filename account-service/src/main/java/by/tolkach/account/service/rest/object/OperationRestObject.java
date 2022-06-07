package by.tolkach.account.service.rest.object;

import by.tolkach.account.dto.operation.OperationType;
import by.tolkach.account.dto.serializer.LongLocalDateTimeDeserializer;
import by.tolkach.account.dto.serializer.LongLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.UUID;

public class OperationRestObject extends EssenceRestObject {

    @JsonSerialize(using = LongLocalDateTimeSerializer.class)
    @JsonDeserialize(using = LongLocalDateTimeDeserializer.class)
    private LocalDateTime date;
    private String description;
    private String category;
    private long value;
    private OperationType type;
    private String currency;
    private UUID account;

    public OperationRestObject() {
    }

    public OperationRestObject(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, LocalDateTime date,
                               String description, String category, long value, OperationType type,
                               String currency, UUID account) {
        super(uuid, dtCreate, dtUpdate);
        this.date = date;
        this.description = description;
        this.category = category;
        this.value = value;
        this.type = type;
        this.currency = currency;
        this.account = account;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public UUID getAccount() {
        return account;
    }

    public void setAccount(UUID account) {
        this.account = account;
    }

    public static class Builder {

        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private LocalDateTime date;
        private String description;
        private String category;
        private long value;
        private OperationType type;
        private String currency;
        private UUID account;

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

        public Builder setDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setCategory(String category) {
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

        public Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder setAccount(UUID account) {
            this.account = account;
            return this;
        }

        public OperationRestObject build() {
            return new OperationRestObject(uuid, dtCreate, dtUpdate, date, description, category, value, type,
                    currency, account);
        }
    }
}
