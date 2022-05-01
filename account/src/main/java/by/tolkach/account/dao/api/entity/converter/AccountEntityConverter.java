package by.tolkach.account.dao.api.entity.converter;

import by.tolkach.account.dao.api.entity.AccountEntity;
import by.tolkach.account.dto.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountEntityConverter implements IEntityConverter<Account, AccountEntity> {

    private final BalanceEntityConverter balanceEntityConverter;

    public AccountEntityConverter(BalanceEntityConverter balanceEntityConverter) {
        this.balanceEntityConverter = balanceEntityConverter;
    }

    @Override
    public AccountEntity toEntity(Account account) {
        return AccountEntity.Builder.createBuilder()
                .setId(account.getId())
                .setTitle(account.getTitle())
                .setDescription(account.getDescription())
                .setType(account.getType())
                .setCurrency(account.getCurrency())
                .setDtCreate(account.getDtCreate())
                .setDtUpdate(account.getDtUpdate())
                .build();
    }

    @Override
    public Account toDto(AccountEntity entity) {
        return Account.Builder.createBuilder()
                .setId(entity.getId())
                .setTitle(entity.getTitle())
                .setDescription(entity.getDescription())
                .setType(entity.getType())
                .setCurrency(entity.getCurrency())
                .setDtCreate(entity.getDtCreate())
                .setDtUpdate(entity.getDtUpdate())
                .build();
    }
}
