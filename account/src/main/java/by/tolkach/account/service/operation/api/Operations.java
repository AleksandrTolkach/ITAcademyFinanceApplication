package by.tolkach.account.service.operation.api;

import by.tolkach.account.dao.api.entity.AccountEntity;
import by.tolkach.account.dao.api.entity.OperationEntity;
import by.tolkach.account.dto.operation.Operation;
import by.tolkach.account.dto.operation.OperationType;

import java.time.LocalDateTime;
import java.util.UUID;

public class Operations {
    public static OperationEntity createParameters(OperationEntity operationEntity, AccountEntity accountEntity) {
        if (operationEntity.getDate() == null) {
            operationEntity.setDate(LocalDateTime.now());
        }
        operationEntity.setUuid(UUID.randomUUID());
        operationEntity.setDtCreate(LocalDateTime.now());
        operationEntity.setDtUpdate(operationEntity.getDtCreate());
        operationEntity.setAccount(accountEntity);
        operationEntity.setType(operationEntity.getValue() >= 0 ? OperationType.RECEIVE : OperationType.SPEND);
        return operationEntity;
    }

    public static OperationEntity updateParameters(Operation operation, OperationEntity operationEntity) {
        if (operation.getDate() == null) {
            operationEntity.setDate(LocalDateTime.now());
        } else {
            operationEntity.setDate(operation.getDate());
        }
        operationEntity.setDescription(operation.getDescription());
        operationEntity.setValue(operation.getValue());
        operationEntity.setCurrency(operation.getCurrency());
        operationEntity.setDtUpdate(LocalDateTime.now());
        return operationEntity;
    }
}
