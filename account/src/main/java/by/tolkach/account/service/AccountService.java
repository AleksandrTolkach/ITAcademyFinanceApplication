package by.tolkach.account.service;

import by.tolkach.account.dao.api.IAccountStorage;
import by.tolkach.account.dto.Account;
import by.tolkach.account.dto.Page;
import by.tolkach.account.dto.SimplePageable;
import by.tolkach.account.service.api.IAccountService;
import by.tolkach.account.dao.api.entity.AccountEntity;
import by.tolkach.account.dao.api.entity.converter.IEntityConverter;
import by.tolkach.account.service.api.Pagination;
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
        this.accountStorage.save(this.accountEntityConverter.toEntity(account));
        return this.read(account.getId());
    }

    @Override
    public Page<Account> read(SimplePageable pageable) {
        List<AccountEntity> accountEntities =
                this.accountStorage.findAllBy(PageRequest.of(pageable.getPage(), pageable.getSize()));
        return Pagination.pageOf(Account.class, AccountEntity.class).properties(accountEntities,
                pageable, (int) this.accountStorage.count(), this.accountEntityConverter);
    }

    @Override
    public Account read(UUID id) {
        return this.accountEntityConverter.toDto(this.accountStorage.findById(id).orElse(null));
    }

    @Override
    public Account update(UUID id, LocalDateTime dtUpdate, Account account) {
        AccountEntity entity = this.accountStorage.findByIdAndDtUpdate(id, dtUpdate);
        entity.setTitle(account.getTitle());
        entity.setDescription(account.getDescription());
        entity.setType(account.getType());
        entity.setCurrency(account.getCurrency());
        entity.setDtUpdate(LocalDateTime.now().withNano(0));
        AccountEntity updatedEntity = this.accountStorage.save(entity);
        return this.accountEntityConverter.toDto(updatedEntity);
    }
}
