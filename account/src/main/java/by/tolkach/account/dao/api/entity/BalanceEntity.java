package by.tolkach.account.dao.api.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "finance", name = "balances")
public class BalanceEntity {

    @Id
    private UUID id;
    @OneToOne
    private AccountEntity account;
    private long sum;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;

    public BalanceEntity() {
    }

    public BalanceEntity(UUID id, AccountEntity account, long balance, LocalDateTime dtCreate, LocalDateTime dtUpdate) {
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

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long number) {
        this.sum = number;
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
        private AccountEntity account;
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

        public Builder setAccount(AccountEntity account) {
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

        public BalanceEntity build() {
            return new BalanceEntity(id, account, sum, dtCreate, dtUpdate);
        }
    }
}
