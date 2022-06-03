package by.tolkach.bot.service;

import by.tolkach.bot.dao.IOperationStorage;
import by.tolkach.bot.dao.entity.OperationEntity;
import by.tolkach.bot.dao.entity.converter.IEntityConverter;
import by.tolkach.bot.dto.Operation;
import by.tolkach.bot.service.api.IOperationService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OperationService implements IOperationService {

    private final IOperationStorage operationStorage;
    private final IEntityConverter<Operation, OperationEntity> operationEntityConverter;

    public OperationService(IOperationStorage operationStorage, IEntityConverter<Operation, OperationEntity> operationEntityConverter) {
        this.operationStorage = operationStorage;
        this.operationEntityConverter = operationEntityConverter;
    }

    @Override
    public Operation save(Operation operation) {
        OperationEntity operationEntity = this.operationEntityConverter.toEntity(operation);
        operationEntity = this.operationStorage.save(operationEntity);
        return this.operationEntityConverter.toDto(operationEntity);
    }

    @Override
    public Operation read(UUID operation) {
        OperationEntity operationEntity = this.operationStorage.findById(operation).orElse(null);
        return this.operationEntityConverter.toDto(operationEntity);
    }
}
