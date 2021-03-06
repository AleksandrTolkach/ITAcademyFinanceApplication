package by.tolkach.account.dao.api.entity;

import by.tolkach.account.dto.operation.OperationType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "application", name = "operations")
public class OperationEntity extends EssenceEntity {

    private LocalDateTime date;
    private String description;
    private UUID category;
    private long value;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    private UUID currency;
    @ManyToOne
    private AccountEntity account;

    public OperationEntity() {
    }

    public OperationEntity(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, LocalDateTime date,
                           String description, UUID category, long value, OperationType type, UUID currency,
                           AccountEntity account) {
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

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public static class Builder {

        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private LocalDateTime date;
        private String description;
        private UUID category;
        private long value;
        private OperationType type;
        private UUID currency;
        private AccountEntity account;

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

        public Builder setAccount(AccountEntity account) {
            this.account = account;
            return this;
        }

        public OperationEntity build() {
            return new OperationEntity(uuid, dtCreate, dtUpdate, date, description, category, value, type, currency, account);
        }
    }
}
