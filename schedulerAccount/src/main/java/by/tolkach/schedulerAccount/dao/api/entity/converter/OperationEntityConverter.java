package by.tolkach.schedulerAccount.dao.api.entity.converter;

import by.tolkach.schedulerAccount.dao.api.entity.OperationEntity;
import by.tolkach.schedulerAccount.dto.Operation;
import org.springframework.stereotype.Component;

@Component
public class OperationEntityConverter implements IEntityConverter<Operation, OperationEntity> {

    @Override
    public OperationEntity toEntity(Operation operation) {
        return OperationEntity.Builder.createBuilder()
                .setUuid(operation.getUuid())
                .setAccount(operation.getAccount())
                .setDescription(operation.getDescription())
                .setValue(operation.getValue())
                .setCurrency(operation.getCurrency())
                .build();
    }

    @Override
    public Operation toDto(OperationEntity entity) {
        return Operation.Builder.createBuilder()
                .setUuid(entity.getUuid())
                .setAccount(entity.getAccount())
                .setDescription(entity.getDescription())
                .setValue(entity.getValue())
                .setCurrency(entity.getCurrency())
                .build();
    }
}
