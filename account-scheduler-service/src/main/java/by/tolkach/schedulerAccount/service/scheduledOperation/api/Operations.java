package by.tolkach.schedulerAccount.service.scheduledOperation.api;

import by.tolkach.schedulerAccount.dao.api.entity.OperationEntity;
import by.tolkach.schedulerAccount.dto.scheduledOperation.Operation;

public class Operations {
    public static OperationEntity updateParameters(OperationEntity operationEntity, Operation operation) {
        operationEntity.setAccount(operation.getAccount());
        operationEntity.setDescription(operation.getDescription());
        operationEntity.setValue(operation.getValue());
        operationEntity.setCurrency(operation.getCurrency());
        return operationEntity;
    }
}
