package by.tolkach.account.dao.api.entity.converter;

import by.tolkach.account.dao.api.entity.OperationEntity;
import by.tolkach.account.dto.operation.Operation;
import org.springframework.stereotype.Component;

@Component
public class OperationEntityConvertor implements IEntityConverter<Operation, OperationEntity> {
    @Override
    public OperationEntity toEntity(Operation operation) {
        return OperationEntity.Builder.createBuilder()
                .setUuid(operation.getUuid())
                .setDtCreate(operation.getDtCreate())
                .setDtUpdate(operation.getDtUpdate())
                .setDate(operation.getDate())
                .setDescription(operation.getDescription())
                .setCategory(operation.getCategory())
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
                .setDate(entity.getDate())
                .setDescription(entity.getDescription())
                .setCategory(entity.getCategory())
                .setValue(entity.getValue())
                .setType(entity.getType())
                .setCurrency(entity.getCurrency())
                .build();
    }
}
