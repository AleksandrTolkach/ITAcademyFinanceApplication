package by.tolkach.account.service.account;

import by.tolkach.account.dao.api.IAccountStorage;
import by.tolkach.account.dto.account.Account;
import by.tolkach.account.dto.Page;
import by.tolkach.account.dto.SimplePageable;
import by.tolkach.account.service.account.api.IAccountService;
import by.tolkach.account.dao.api.entity.AccountEntity;
import by.tolkach.account.dao.api.entity.converter.IEntityConverter;
import by.tolkach.account.service.api.Pagination;
import by.tolkach.account.service.api.exception.NotFoundError;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService implements IAccountService {

    private final IAccountStorage accountStorage;
    private final IEntityConverter<Account, AccountEntity> accountEntityConverter;

    public AccountService(IAccountStorage accountStorage,
                          IEntityConverter<Account, AccountEntity> accountEntityConverter) {
        this.accountStorage = accountStorage;
        this.accountEntityConverter = accountEntityConverter;
    }

    @Override
    public Account create(Account account) {
        AccountEntity createdAccountEntity = this.accountStorage.save(this.accountEntityConverter.toEntity(account));
        return this.accountEntityConverter.toDto(createdAccountEntity);
    }

    @Override
    public Page<Account> read(SimplePageable pageable) {
        List<AccountEntity> accountEntities =
                this.accountStorage.findAllBy(PageRequest.of(pageable.getPage(), pageable.getSize()));
        return Pagination.pageOf(Account.class, AccountEntity.class).properties(accountEntities,
                pageable, this.accountStorage.count(), this.accountEntityConverter);
    }

    @Override
    public Account read(UUID id) {
        Account account = this.accountEntityConverter.toDto(this.accountStorage.findById(id).orElse(null));
        if (account == null) {
            throw new NotFoundError("Счета с указанным id не существует.");
        }
        return account;
    }

    @Override
    public Account update(UUID id, LocalDateTime dtUpdate, Account account) {
        AccountEntity accountEntity = this.accountStorage.findById(id).orElse(null);
        if (accountEntity == null) {
            throw new NotFoundError("Счета с указанным id не существует.");
        }
        if (!accountEntity.getDtUpdate().equals(dtUpdate)) {
            throw new NotFoundError("Запись устарела. Пожалуйста обновите запрос.");
        }
        AccountEntity updatedAccountEntity = this.accountStorage.save(this.setNewParameters(account, accountEntity));
        return this.accountEntityConverter.toDto(updatedAccountEntity);
    }

    public AccountEntity setNewParameters(Account account, AccountEntity accountEntity) {
        accountEntity.setTitle(account.getTitle());
        accountEntity.setDescription(account.getDescription());
        accountEntity.setType(account.getType());
        accountEntity.setCurrency(account.getCurrency());
        accountEntity.setDtUpdate(LocalDateTime.now().withNano(0));
        return accountEntity;
    }
}
