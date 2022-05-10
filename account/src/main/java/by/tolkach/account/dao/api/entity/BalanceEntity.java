package by.tolkach.account.dao.api.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "application", name = "balances")
public class BalanceEntity extends EssenceEntity {

    @OneToOne
    private AccountEntity account;
    private long sum;

    public BalanceEntity() {
    }

    public BalanceEntity(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, AccountEntity account, long sum) {
        super(uuid, dtCreate, dtUpdate);
        this.account = account;
        this.sum = sum;
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

    public static class Builder {

        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private AccountEntity account;
        private long sum;

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

        public Builder setAccount(AccountEntity account) {
            this.account = account;
            return this;
        }

        public Builder setSum(long sum) {
            this.sum = sum;
            return this;
        }

        public BalanceEntity build() {
            return new BalanceEntity(uuid, dtCreate, dtUpdate, account, sum);
        }
    }
}
