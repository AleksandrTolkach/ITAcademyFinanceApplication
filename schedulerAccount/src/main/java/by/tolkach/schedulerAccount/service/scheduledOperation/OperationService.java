package by.tolkach.schedulerAccount.service.scheduledOperation;

import by.tolkach.schedulerAccount.dao.api.IOperationStorage;
import by.tolkach.schedulerAccount.dao.api.entity.OperationEntity;
import by.tolkach.schedulerAccount.dao.api.entity.converter.IEntityConverter;
import by.tolkach.schedulerAccount.dto.scheduledOperation.Operation;
import by.tolkach.schedulerAccount.dto.exception.NotFoundError;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.IOperationService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OperationService implements IOperationService {

    private final IOperationStorage operationStorage;
    private final IEntityConverter<Operation, OperationEntity> operationEntityConverter;

    public OperationService(IOperationStorage operationStorage,
                            IEntityConverter<Operation, OperationEntity> operationEntityConverter) {
        this.operationStorage = operationStorage;
        this.operationEntityConverter = operationEntityConverter;
    }

    @Override
    public Operation create(Operation operation) {
        operation.setUuid(UUID.randomUUID());
        OperationEntity savedOperationEntity =
                this.operationStorage.save(this.operationEntityConverter.toEntity(operation));
        return this.operationEntityConverter.toDto(savedOperationEntity);
    }

    @Override
    public Operation read(UUID operationId) {
        OperationEntity operationEntity = this.operationStorage.findById(operationId).orElse(null);
        if (operationEntity == null) {
            throw new NotFoundError("Операция отсутствует в базе");
        }
        return this.operationEntityConverter.toDto(operationEntity);
    }

    @Override
    public Operation update(UUID operationId, Operation operation) {
        OperationEntity operationEntity = this.operationStorage.findById(operationId).orElse(null);
        if (operationEntity == null) {
            throw new NotFoundError("Операция отсутствует в базе");
        }
        operationEntity = this.updateOperationParameters(operationEntity, operation);
        return this.operationEntityConverter.toDto(this.operationStorage.save(operationEntity));
    }

    public OperationEntity updateOperationParameters(OperationEntity operationEntity, Operation operation) {
        operationEntity.setAccount(operation.getAccount());
        operationEntity.setDescription(operation.getDescription());
        operationEntity.setValue(operation.getValue());
        operationEntity.setCurrency(operation.getCurrency());
        return operationEntity;
    }
}
