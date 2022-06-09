package by.tolkach.report.service.helper;

import by.tolkach.report.dao.api.entity.AccountEntity;
import by.tolkach.report.dao.api.entity.converter.IEntityConverter;
import by.tolkach.report.dao.api.helper.IAccountStorage;
import by.tolkach.report.dto.Account;
import by.tolkach.report.dto.exception.NotFoundException;
import by.tolkach.report.service.helper.api.IAccountService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService implements IAccountService {

    public final IAccountStorage accountStorage;
    private final IEntityConverter<Account, AccountEntity> accountEntityConverter;

    public AccountService(IAccountStorage accountStorage,
                          IEntityConverter<Account, AccountEntity> accountEntityConverter) {
        this.accountStorage = accountStorage;
        this.accountEntityConverter = accountEntityConverter;
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = this.accountEntityConverter.toEntity(account);
        accountEntity = this.accountStorage.save(accountEntity);
        return this.accountEntityConverter.toDto(accountEntity);
    }

    @Override
    public Account read(UUID accountId) {
        AccountEntity accountEntity = this.accountStorage.findById(accountId).orElse(null);
        if (accountEntity == null) {
            throw new NotFoundException("Счета с Id " + accountId + " не существует.");
        }
        return this.accountEntityConverter.toDto(accountEntity);
    }

    @Override
    public Account update(Account account) {
        AccountEntity accountEntity = this.accountStorage.findById(account.getUuid()).orElse(null);
        if (accountEntity == null) {
            throw new NotFoundException("Счета с таким Id не существует");
        }
        this.updateAccountParameters(account, accountEntity);
        accountEntity= this.accountStorage.save(accountEntity);
        return this.accountEntityConverter.toDto(accountEntity);
    }

    private AccountEntity updateAccountParameters(Account account, AccountEntity accountEntity)  {
        accountEntity.setDtCreate(account.getDtCreate());
        accountEntity.setDtUpdate(account.getDtUpdate());
        accountEntity.setTitle(account.getTitle());
        accountEntity.setDescription(account.getDescription());
        return accountEntity;
    }
}
