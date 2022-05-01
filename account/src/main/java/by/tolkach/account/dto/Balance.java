package by.tolkach.account.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class Balance {

    private UUID id;
    private UUID account;
    private long sum;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;

    public Balance() {
    }

    public Balance(UUID id, UUID account, long balance, LocalDateTime dtCreate, LocalDateTime dtUpdate) {
        this.id = id;
        this.account = account;
        this.sum = balance;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccount() {
        return account;
    }

    public void setAccount(UUID account) {
        this.account = account;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
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

    public static class Builder {

        private UUID id;
        private UUID account;
        private long sum;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setAccount(UUID account) {
            this.account = account;
            return this;
        }

        public Builder setSum(long sum) {
            this.sum = sum;
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

        public Balance build() {
            return new Balance(id, account, sum, dtCreate, dtUpdate);
        }
    }
}
