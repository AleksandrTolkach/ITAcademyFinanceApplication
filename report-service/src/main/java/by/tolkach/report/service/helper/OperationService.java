package by.tolkach.report.service.helper;

import by.tolkach.report.dao.api.helper.IOperationStorage;
import by.tolkach.report.dao.api.entity.operation.OperationEntity;
import by.tolkach.report.dao.api.entity.converter.IEntityConverter;
import by.tolkach.report.dto.operation.Operation;
import by.tolkach.report.dto.report.Param;
import by.tolkach.report.service.helper.api.IOperationCategoryService;
import by.tolkach.report.service.helper.api.IOperationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OperationService implements IOperationService {

    private final IOperationStorage operationStorage;
    private final IEntityConverter<Operation, OperationEntity> operationEntityConverter;
    private final IOperationCategoryService operationCategoryService;

    public OperationService(IOperationStorage operationStorage, IEntityConverter<Operation, OperationEntity> operationEntityConverter,
                            IOperationCategoryService operationCategoryService) {
        this.operationStorage = operationStorage;
        this.operationEntityConverter = operationEntityConverter;
        this.operationCategoryService = operationCategoryService;
    }

    @Override
    public List<Operation> readByBalance(Param param) {
        List<OperationEntity> operationEntities = this.operationStorage.findAllByAccountIn(param.getAccounts());
        return this.convertEntities(operationEntities);
    }

    @Override
    public List<Operation> readByDate(Param param) {
        List<String> operationCategories = this.matchOperationCategory(param);
        List<OperationEntity> operationEntities = this.operationStorage.
                findAllByAccountInAndDateGreaterThanAndDateLessThanAndCategoryInOrderByDate(param.getAccounts(),
                param.getFrom(), param.getTo(), operationCategories);
        return this.convertEntities(operationEntities);
    }

    @Override
    public List<Operation> readByCategory(Param param) {
        List<String> operationCategories = this.matchOperationCategory(param);
        List<OperationEntity> operationEntities
                = this.operationStorage
                .findAllByAccountInAndDateGreaterThanAndDateLessThanAndCategoryInOrderByCategory(param.getAccounts(),
                        param.getFrom(), param.getTo(), operationCategories);
        return this.convertEntities(operationEntities);
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

    private OperationEntity updateOperationParameters(Operation operation, OperationEntity operationEntity) {
        operationEntity.setDtUpdate(operation.getDtUpdate());
        operationEntity.setDate(operation.getDate());
        operationEntity.setDescription(operation.getDescription());
        operationEntity.setCurrency(operation.getCurrency());
        operationEntity.setCategory(operation.getCategory());
        operationEntity.setType(operation.getType());
        operationEntity.setValue(operation.getValue());
        operationEntity.setAccount(operation.getAccount());
        return operationEntity;
    }

    private List<String> matchOperationCategory(Param param) {
        List<String> operationCategories = new ArrayList<>();
        for(UUID category: param.getCategories()) {
            operationCategories.add(this.operationCategoryService.read(category).getTitle());
        }
        return operationCategories;
    }

    private List<Operation> convertEntities(List<OperationEntity> operationEntities) {
        List<Operation> operations = new ArrayList<>();
        for (OperationEntity operationEntity: operationEntities) {
            operations.add(this.operationEntityConverter.toDto(operationEntity));
        }
        return operations;
    }
}
