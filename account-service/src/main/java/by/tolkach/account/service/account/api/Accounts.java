package by.tolkach.account.service.account.api;

import by.tolkach.account.dao.api.entity.AccountEntity;
import by.tolkach.account.dao.api.entity.BalanceEntity;
import by.tolkach.account.dto.account.Account;

import java.time.LocalDateTime;

public class Accounts {
    public static AccountEntity updateParameters(Account account, AccountEntity accountEntity) {
        accountEntity.setTitle(account.getTitle());
        accountEntity.setDescription(account.getDescription());
        accountEntity.setType(account.getType());
        accountEntity.setCurrency(account.getCurrency());
        accountEntity.setDtUpdate(LocalDateTime.now().withNano(0));
        return accountEntity;
    }

    public static BalanceEntity createBalanceParameters(AccountEntity accountEntity) {
        return BalanceEntity.Builder.createBuilder()
                .setAccount(accountEntity)
                .setSum(0L)
                .setDtCreate(accountEntity.getDtCreate())
                .setDtUpdate(accountEntity.getDtCreate())
                .build();
    }
}
