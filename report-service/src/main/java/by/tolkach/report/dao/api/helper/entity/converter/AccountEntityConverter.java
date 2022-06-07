package by.tolkach.report.dao.api.helper.entity.converter;

import by.tolkach.report.dao.api.entity.AccountEntity;
import by.tolkach.report.dao.api.entity.converter.IEntityConverter;
import by.tolkach.report.dto.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountEntityConverter implements IEntityConverter<Account, AccountEntity> {

    @Override
    public AccountEntity toEntity(Account account) {
        return AccountEntity.Builder.createBuilder()
                .setUuid(account.getUuid())
                .setTitle(account.getTitle())
                .setDescription(account.getDescription())
                .setDtCreate(account.getDtCreate())
                .setDtUpdate(account.getDtUpdate())
                .build();
    }

    @Override
    public Account toDto(AccountEntity entity) {
        return Account.Builder.createBuilder()
                .setUuid(entity.getUuid())
                .setTitle(entity.getTitle())
                .setDescription(entity.getDescription())
                .setDtCreate(entity.getDtCreate())
                .setDtUpdate(entity.getDtUpdate())
                .build();
    }
}
