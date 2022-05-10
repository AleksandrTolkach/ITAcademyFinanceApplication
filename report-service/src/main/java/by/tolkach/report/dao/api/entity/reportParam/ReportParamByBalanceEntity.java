package by.tolkach.report.dao.api.entity.reportParam;

import by.tolkach.report.dao.api.entity.AccountsEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public class ReportParamByBalanceEntity {

    @Id
    @GeneratedValue
    private UUID uuid;
    private AccountsEntity accounts;

    public ReportParamByBalanceEntity() {
    }

    public ReportParamByBalanceEntity(AccountsEntity accounts) {
        this.accounts = accounts;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public AccountsEntity getAccounts() {
        return accounts;
    }

    public void setAccounts(AccountsEntity accounts) {
        this.accounts = accounts;
    }
}
