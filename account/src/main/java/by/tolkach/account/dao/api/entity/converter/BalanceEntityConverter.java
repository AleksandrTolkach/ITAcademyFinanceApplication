package by.tolkach.account.dao.api.entity.converter;

import by.tolkach.account.dao.api.entity.BalanceEntity;
import by.tolkach.account.dto.Balance;
import org.springframework.stereotype.Component;

@Component
public class BalanceEntityConverter implements IEntityConverter<Balance, BalanceEntity> {
    @Override
    public BalanceEntity toEntity(Balance balance) {
        return BalanceEntity.Builder.createBuilder()
                .setId(balance.getId())
                .setSum(balance.getSum())
                .setDtCreate(balance.getDtCreate())
                .setDtUpdate(balance.getDtUpdate())
                .build();
    }

    @Override
    public Balance toDto(BalanceEntity entity) {
        return Balance.Builder.createBuilder()
                .setId(entity.getId())
                .setSum(entity.getSum())
                .setDtCreate(entity.getDtCreate())
                .setDtUpdate(entity.getDtUpdate())
                .setAccount(entity.getAccount().getId())
                .build();
    }
}
