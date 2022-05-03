package by.tolkach.account.service;

import by.tolkach.account.dao.api.IBalanceStorage;
import by.tolkach.account.dao.api.entity.AccountEntity;
import by.tolkach.account.dao.api.entity.BalanceEntity;
import by.tolkach.account.dao.api.entity.converter.IEntityConverter;
import by.tolkach.account.dto.Account;
import by.tolkach.account.dto.Balance;
import by.tolkach.account.service.api.IBalanceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BalanceService implements IBalanceService {

    private final IBalanceStorage balanceStorage;
    private final IEntityConverter<Balance, BalanceEntity> balanceEntityConverter;
    private final IEntityConverter<Account, AccountEntity> accountEntityConverter;

    public BalanceService(IBalanceStorage balanceStorage,
                          IEntityConverter<Balance, BalanceEntity> balanceEntityConverter,
                          IEntityConverter<Account, AccountEntity> accountEntityConverter) {
        this.balanceStorage = balanceStorage;
        this.balanceEntityConverter = balanceEntityConverter;
        this.accountEntityConverter = accountEntityConverter;
    }

    @Override
    public Balance create(Account account) {
        AccountEntity accountEntity = this.accountEntityConverter
                .toEntity(account);
        this.balanceStorage.save(BalanceEntity.Builder.createBuilder()
                .setUuid(UUID.randomUUID())
                .setAccount(accountEntity)
                .setSum(0L)
                .setDtCreate(accountEntity.getDtCreate())
                .setDtUpdate(accountEntity.getDtCreate())
                .build());
        return this.read(accountEntity.getUuid());
    }

    @Override
    public Balance read(UUID accountId) {
        BalanceEntity balanceEntity = this.balanceStorage.findByAccount_Uuid(accountId);
        Balance balance = this.balanceEntityConverter.toDto(balanceEntity);
        return balance;
    }

    @Override
    public Balance update(UUID  accountId, LocalDateTime dtUpdate, Long num) {
        BalanceEntity balanceEntity = this.balanceStorage.findByAccount_UuidAndDtUpdate(accountId, dtUpdate);
        balanceEntity.setSum(balanceEntity.getSum() + num);
        balanceEntity.setDtUpdate(LocalDateTime.now().withNano(0));
        this.balanceStorage.save(balanceEntity);
        return this.read(accountId);
    }
}
