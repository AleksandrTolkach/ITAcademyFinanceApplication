package by.tolkach.account.service.account;

import by.tolkach.account.dao.api.IBalanceStorage;
import by.tolkach.account.dao.api.entity.AccountEntity;
import by.tolkach.account.dao.api.entity.BalanceEntity;
import by.tolkach.account.dao.api.entity.converter.IEntityConverter;
import by.tolkach.account.dto.account.Account;
import by.tolkach.account.dto.account.Balance;
import by.tolkach.account.service.account.api.IBalanceService;
import by.tolkach.account.service.api.exception.NotFoundError;
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
        AccountEntity accountEntity = this.accountEntityConverter.toEntity(account);
        BalanceEntity createdBalanceEntity = this.balanceStorage.save(this.createBalanceParameters(accountEntity));
        return this.balanceEntityConverter.toDto(createdBalanceEntity);
    }

    @Override
    public Balance read(UUID accountId) {
        BalanceEntity balanceEntity = this.balanceStorage.findByAccount_Uuid(accountId);
        if (balanceEntity == null) {
            throw new NotFoundError("Указан неверный ID счета.");
        }
        return this.balanceEntityConverter.toDto(balanceEntity);
    }

    @Override
    public Balance update(UUID  accountId, LocalDateTime dtUpdate, Long num) {
        BalanceEntity balanceEntity = this.balanceStorage.findByAccount_Uuid(accountId);
        if (balanceEntity == null) {
            throw new NotFoundError("У счета отсутствует баланс.");
        }
        if (!balanceEntity.getDtUpdate().equals(dtUpdate)) {
            throw new NotFoundError("Запись устарела. Пожалуйста обновите запрос.");
        }
        balanceEntity.setSum(balanceEntity.getSum() + num);
        balanceEntity.setDtUpdate(LocalDateTime.now().withNano(0));
        BalanceEntity updatedEntity = this.balanceStorage.save(balanceEntity);
        return this.balanceEntityConverter.toDto(updatedEntity);
    }

    public BalanceEntity createBalanceParameters(AccountEntity accountEntity) {
        return BalanceEntity.Builder.createBuilder()
                .setAccount(accountEntity)
                .setSum(0L)
                .setDtCreate(accountEntity.getDtCreate())
                .setDtUpdate(accountEntity.getDtCreate())
                .build();
    }
}
