package by.tolkach.account.dao.api.entity.converter;

import by.tolkach.account.dao.api.entity.OperationEntity;
import by.tolkach.account.dto.Operation;
import org.springframework.stereotype.Component;

@Component
public class OperationEntityConvertor implements IEntityConverter<Operation, OperationEntity> {
    @Override
    public OperationEntity toEntity(Operation operation) {
        return OperationEntity.Builder.createBuilder()
                .setUuid(operation.getUuid())
                .setDtCreate(operation.getDtCreate())
                .setDtUpdate(operation.getDtUpdate())
                .setDescription(operation.getDescription())
                .setValue(operation.getValue())
                .setType(operation.getType())
                .setCurrency(operation.getCurrency())
                .build();
    }

    @Override
    public Operation toDto(OperationEntity entity) {
        return Operation.Builder.createBuilder()
                .setUuid(entity.getUuid())
                .setDtCreate(entity.getDtCreate())
                .setDtUpdate(entity.getDtUpdate())
                .setDescription(entity.getDescription())
                .setValue(entity.getValue())
                .setType(entity.getType())
                .setCurrency(entity.getCurrency())
                .build();
    }
}
