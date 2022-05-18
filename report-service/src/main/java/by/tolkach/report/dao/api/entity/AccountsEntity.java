package by.tolkach.report.dao.api.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class AccountsEntity implements Serializable {

    private List<UUID> accounts;

    public AccountsEntity() {
    }

    public AccountsEntity(List<UUID> accounts) {
        this.accounts = accounts;
    }

    public List<UUID> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<UUID> accounts) {
        this.accounts = accounts;
    }
}
