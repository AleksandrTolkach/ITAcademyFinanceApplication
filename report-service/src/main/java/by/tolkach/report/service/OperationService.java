package by.tolkach.report.service;

import by.tolkach.report.dao.api.IOperationStorage;
import by.tolkach.report.dao.api.entity.OperationEntity;
import by.tolkach.report.dao.api.entity.converter.IEntityConverter;
import by.tolkach.report.dto.Operation;
import by.tolkach.report.dto.reportParam.ExtendedParam;
import by.tolkach.report.service.api.IOperationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public List<Operation> readByBalance(ExtendedParam extendedParam) {
        List<Operation> operations = new ArrayList<>();
        for (UUID account: extendedParam.getAccounts()) {
            List<OperationEntity> operationEntities = this.operationStorage.findAllByAccount(account);
            for (OperationEntity operationEntity: operationEntities) {
                operations.add(this.operationEntityConverter.toDto(operationEntity));
            }
        }
        return operations;
    }

    @Override
    public void save(Operation operation) {
        OperationEntity operationEntity = this.operationEntityConverter.toEntity(operation);
        this.operationStorage.save(operationEntity);
    }

    @Override
    public void update(Operation operation) {
        OperationEntity operationEntity = this.operationStorage.findById(operation.getUuid()).orElse(null);
        this.operationStorage.save(this.updateOperationParameters(operation, operationEntity));
    }

    @Override
    public void delete(UUID operationId, UUID accountId) {
        OperationEntity operationEntity = this.operationStorage.findByUuidAndAccount(operationId, accountId);
        this.operationStorage.delete(operationEntity);
    }

    public OperationEntity updateOperationParameters(Operation operation, OperationEntity operationEntity) {
        operationEntity.setDtUpdate(operation.getDtUpdate());
        operationEntity.setDate(operation.getDate());
        operationEntity.setCategory(operation.getCategory());
        operationEntity.setCurrency(operation.getCurrency());
        operationEntity.setDescription(operation.getDescription());
        operationEntity.setType(operation.getType());
        operationEntity.setValue(operation.getValue());
        operationEntity.setAccount(operation.getAccount());
        return operationEntity;
    }
}
