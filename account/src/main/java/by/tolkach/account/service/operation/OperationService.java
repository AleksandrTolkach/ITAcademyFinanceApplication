package by.tolkach.account.service.operation;

import by.tolkach.account.dao.api.IOperationStorage;
import by.tolkach.account.dto.*;
import by.tolkach.account.dto.account.Account;
import by.tolkach.account.dto.operation.Operation;
import by.tolkach.account.dto.operation.OperationType;
import by.tolkach.account.service.account.api.IAccountService;
import by.tolkach.account.service.api.exception.NotFoundError;
import by.tolkach.account.service.operation.api.IOperationService;
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
        OperationEntity operationEntity = this.operationEntityConverter.toEntity(operation);
        this.createOperationParameters(operationEntity, accountId);
        OperationEntity createdOperationEntity = this.operationStorage.save(operationEntity);
        return this.operationEntityConverter.toDto(createdOperationEntity);
    }

    @Override
    public Page<Operation> read(UUID id, SimplePageable pageable) {
        List<OperationEntity> operationEntities = this.operationStorage.findAllByAccount_Uuid(id,
                PageRequest.of(pageable.getPage(), pageable.getSize()));
        return Pagination.pageOf(Operation.class, OperationEntity.class)
                .properties(operationEntities, pageable, operationEntities.size(),
                        this.operationEntityConverter);
    }

    @Override
    public Operation read(UUID operationId, UUID accountId) {
        OperationEntity operationEntity = this.operationStorage.findByUuidAndAccount_Uuid(operationId, accountId);
        if (operationEntity == null) {
            throw new NotFoundError("Указанного счета не существует.");
        }
        return this.operationEntityConverter.toDto(operationEntity);
    }

    @Override
    public Operation update(UUID accountId, UUID operationId, LocalDateTime dtUpdate, Operation operation) {
        OperationEntity operationEntity = this.operationStorage.findByUuidAndAccount_Uuid(operationId, accountId);
        if (operation == null) {
            throw new NotFoundError("Указанного счета не существует.");
        }
        if (!operationEntity.getDtUpdate().equals(dtUpdate)) {
            throw new NotFoundError("Запись устарела. Пожалуйста обновите запрос.");
        }
        this.updateOperationParameters(operation, operationEntity);
        OperationEntity updatedEntity = this.operationStorage.save(operationEntity);
        return this.operationEntityConverter.toDto(updatedEntity);
    }

    @Override
    public void delete(UUID accountId, UUID operationId, LocalDateTime dtUpdate) {
        OperationEntity operationEntity =
                this.operationStorage.findByUuidAndAccount_Uuid(operationId, accountId);
        if (operationEntity == null) {
            throw new NotFoundError("Указаны неверные id операции или счета");
        }
        if (!operationEntity.getDtUpdate().equals(dtUpdate)) {
            throw new NotFoundError("Запись устарела. Пожалуйста обновите запрос.");
        }
        this.operationStorage.delete(operationEntity);
    }

    public OperationEntity createOperationParameters(OperationEntity operationEntity, UUID accountId) {
        AccountEntity accountEntity = this.accountEntityConverter.toEntity(this.accountService.read(accountId));
        operationEntity.setUuid(UUID.randomUUID());
        operationEntity.setDtCreate(LocalDateTime.now().withNano(0));
        operationEntity.setDtUpdate(operationEntity.getDtCreate());
        operationEntity.setAccount(accountEntity);
        operationEntity.setType(operationEntity.getValue() >= 0 ? OperationType.RECEIVE : OperationType.SPEND);
        return operationEntity;
    }

    public OperationEntity updateOperationParameters(Operation operation, OperationEntity operationEntity) {
        operationEntity.setDescription(operation.getDescription());
        operationEntity.setValue(operation.getValue());
        operationEntity.setCurrency(operation.getCurrency());
        operationEntity.setDtUpdate(LocalDateTime.now().withNano(0));
        return operationEntity;
    }
}
