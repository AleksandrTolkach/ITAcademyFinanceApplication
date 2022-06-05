package by.tolkach.schedulerAccount.service.scheduledOperation;

import by.tolkach.schedulerAccount.dao.api.IOperationStorage;
import by.tolkach.schedulerAccount.dao.api.entity.OperationEntity;
import by.tolkach.schedulerAccount.dao.api.entity.converter.IEntityConverter;
import by.tolkach.schedulerAccount.dto.scheduledOperation.Operation;
import by.tolkach.schedulerAccount.dto.exception.NotFoundException;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.IOperationService;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.Operations;
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
        OperationEntity operationEntity =
                this.operationStorage.save(this.operationEntityConverter.toEntity(operation));
        return this.operationEntityConverter.toDto(operationEntity);
    }

    @Override
    public Operation read(UUID operationId) {
        OperationEntity operationEntity = this.operationStorage.findById(operationId).orElse(null);
        if (operationEntity == null) {
            throw new NotFoundException("Операция отсутствует в базе");
        }
        return this.operationEntityConverter.toDto(operationEntity);
    }

    @Override
    public Operation update(UUID operationId, Operation operation) {
        OperationEntity operationEntity = this.operationStorage.findById(operationId).orElse(null);
        if (operationEntity == null) {
            throw new NotFoundException("Операция отсутствует в базе");
        }
        Operations.updateParameters(operationEntity, operation);
        return this.operationEntityConverter.toDto(this.operationStorage.save(operationEntity));
    }
}
