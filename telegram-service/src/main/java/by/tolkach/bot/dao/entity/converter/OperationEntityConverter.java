package by.tolkach.bot.dao.entity.converter;

import by.tolkach.bot.dao.entity.OperationEntity;
import by.tolkach.bot.dto.Operation;
import org.springframework.stereotype.Component;

@Component
public class OperationEntityConverter implements IEntityConverter<Operation, OperationEntity> {

    @Override
    public OperationEntity toEntity(Operation operation) {
        return OperationEntity.Builder.createBuilder()
                .setUuid(operation.getUuid())
                .setDtUpdate(operation.getDtUpdate())
                .setDtCreate(operation.getDtCreate())
                .setCategory(operation.getCategory())
                .setAccount(operation.getAccount())
                .setDescription(operation.getDescription())
                .setValue(operation.getValue())
                .setCurrency(operation.getCurrency())
                .build();
    }

    @Override
    public Operation toDto(OperationEntity restObject) {
        return Operation.Builder.createBuilder()
                .setUuid(restObject.getUuid())
                .setDtUpdate(restObject.getDtUpdate())
                .setDtCreate(restObject.getDtCreate())
                .setCategory(restObject.getCategory())
                .setDescription(restObject.getDescription())
                .setValue(restObject.getValue())
                .setAccount(restObject.getAccount())
                .setCurrency(restObject.getCurrency())
                .build();
    }
}
