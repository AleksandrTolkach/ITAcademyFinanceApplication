package by.tolkach.account.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Account extends Essence {

    private String title;
    private String description;
    private BigDecimal balance = new BigDecimal(0);
    private AccountType type;
    private Currency currency;

    public Account() {
    }

    public Account(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String title, String description,
                   BigDecimal balance, AccountType type, Currency currency) {
        super(uuid, dtCreate, dtUpdate);
        this.title = title;
        this.description = description;
        this.balance = balance;
        this.type = type;
        this.currency = currency;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public static class Builder {

        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private String title;
        private String description;
        private BigDecimal balance;
        private AccountType type;
        private Currency currency;

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

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder setType(AccountType type) {
            this.type = type;
            return this;
        }

        public Builder setCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Account build() {
            return new Account(uuid, dtCreate, dtUpdate, title, description, balance, type, currency);
        }
    }
}
