package by.tolkach.account.service;

import by.tolkach.account.dao.api.IOperationStorage;
import by.tolkach.account.dto.*;
import by.tolkach.account.service.api.IAccountService;
import by.tolkach.account.service.api.IOperationService;
import by.tolkach.account.service.api.IValidationService;
import by.tolkach.account.dao.api.entity.AccountEntity;
import by.tolkach.account.dao.api.entity.OperationEntity;
import by.tolkach.account.dao.api.entity.converter.IEntityConverter;
import by.tolkach.account.service.api.Pagination;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OperationService implements IOperationService {

    private final IAccountService accountService;
    private final IOperationStorage operationStorage;
    private final IValidationService<Operation> operationValidationService;
    private final IEntityConverter<Account, AccountEntity> accountEntityConverter;
    private final IEntityConverter<Operation, OperationEntity> operationEntityConverter;

    public OperationService(IAccountService accountService,
                            IOperationStorage operationStorage,
                            IEntityConverter<Account, AccountEntity> accountEntityConvertor,
                            IEntityConverter<Operation, OperationEntity> operationEntityConvertor,
                            IValidationService<Operation> validationService) {
        this.accountService = accountService;
        this.operationStorage = operationStorage;
        this.accountEntityConverter = accountEntityConvertor;
        this.operationEntityConverter = operationEntityConvertor;
        this.operationValidationService = validationService;
    }

    @Override
    public Operation create(Operation operation, UUID accountId) {
        operation = this.operationValidationService.validate(operation);
        AccountEntity accountEntity = this.accountEntityConverter.toEntity(this.accountService.read(accountId));
        OperationEntity operationEntity = this.operationEntityConverter.toEntity(operation);
        operationEntity.setUuid(UUID.randomUUID());
        operationEntity.setDtCreate(LocalDateTime.now().withNano(0));
        operationEntity.setDtUpdate(operationEntity.getDtCreate());
        operationEntity.setAccount(accountEntity);
        operationEntity.setType(OperationType.RECEIVE);
        this.operationStorage.save(operationEntity);
        return this.read(operationEntity.getUuid());
    }

    @Override
    public Page<Operation> read(UUID id, SimplePageable pageable) {
        List<OperationEntity> operationEntities = this.operationStorage.findAllByAccount_Uuid(id,
                PageRequest.of(pageable.getPage(), pageable.getSize()));
        return Pagination.pageOf(Operation.class, OperationEntity.class)
                .properties(operationEntities, pageable, (int) this.operationStorage.count(),
                        this.operationEntityConverter);
    }

    @Override
    public Operation read(UUID id) {
        return this.operationEntityConverter.toDto(this.operationStorage.findById(id).orElse(null));
    }

    @Override
    public Operation update(UUID accountId, UUID operationId, LocalDateTime dtUpdate, Operation operation) {
        OperationEntity operationEntity =
                this.operationStorage.findByAccount_UuidAndUuidAndDtUpdate(accountId, operationId, dtUpdate);
        operationEntity.setDescription(operation.getDescription());
        operationEntity.setValue(operation.getValue());
        operationEntity.setCurrency(operation.getCurrency());
        operationEntity.setDtUpdate(LocalDateTime.now().withNano(0));
        this.operationStorage.save(operationEntity);
        return this.read(operationId);
    }
}
