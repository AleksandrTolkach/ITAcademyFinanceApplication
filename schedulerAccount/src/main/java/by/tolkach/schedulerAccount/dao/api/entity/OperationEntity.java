package by.tolkach.schedulerAccount.dao.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import java.util.UUID;

@Entity
@Table(schema = "application", name = "operations")
public class OperationEntity {

    @Id
    private UUID uuid;
    private UUID account;
    private UUID category;
    private String description;
    private BigDecimal value;
    private UUID currency;

    public OperationEntity() {
    }

    public OperationEntity(UUID uuid, UUID account, UUID category, String description, BigDecimal value, UUID currency) {
        this.uuid = uuid;
        this.account = account;
        this.category = category;
        this.description = description;
        this.value = value;
        this.currency = currency;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getAccount() {
        return account;
    }

    public void setAccount(UUID accountId) {
        this.account = accountId;
    }

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
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
        private UUID account;
        private UUID category;
        private String description;
        private BigDecimal value;
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

        public Builder setAccount(UUID account) {
            this.account = account;
            return this;
        }

        public Builder setCategory(UUID category) {
            this.category = category;
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
            return new OperationEntity(uuid, account, category, description, value, currency);
        }
    }
}
