package by.tolkach.bot.dao.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "application", name = "operations")
public class OperationEntity extends EssenceEntity {

    private LocalDateTime date;
    private UUID category;
    private UUID account;
    private String description;
    private BigDecimal value = new BigDecimal(0L);
    private UUID currency;

    public OperationEntity() {
    }

    public OperationEntity(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, LocalDateTime date,
                           UUID category, UUID account, String description, BigDecimal value, UUID currency) {
        super(uuid, dtCreate, dtUpdate);
        this.date = date;
        this.category = category;
        this.account = account;
        this.description = description;
        this.value = value;
        this.currency = currency;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }

    public UUID getAccount() {
        return account;
    }

    public void setAccount(UUID account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public UUID getCurrency() {
        return currency;
    }

    public void setCurrency(UUID currency) {
        this.currency = currency;
    }

    public static class Builder {

        private UUID uuid;
        private LocalDateTime date;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private UUID category;
        private UUID account;
        private String description;
        private BigDecimal value = new BigDecimal(0L);
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

        public Builder setDate(LocalDateTime date) {
            this.date = date;
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

        public Builder setCategory(UUID category) {
            this.category = category;
            return this;
        }

        public Builder setAccount(UUID account) {
            this.account = account;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setValue(BigDecimal value) {
            this.value = value;
            return this;
        }

        public Builder setCurrency(UUID currency) {
            this.currency = currency;
            return this;
        }

        public OperationEntity build() {
            return new OperationEntity(uuid, dtCreate, dtUpdate, date, category,
                    account, description, value, currency);
        }
    }
}
