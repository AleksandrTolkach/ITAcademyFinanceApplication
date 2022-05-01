package by.tolkach.account.service;

import by.tolkach.account.dto.Operation;
import by.tolkach.account.dto.Page;
import by.tolkach.account.dto.SimplePageable;
import by.tolkach.account.service.api.IAccountService;
import by.tolkach.account.service.api.IBalanceService;
import by.tolkach.account.service.api.IOperationService;
import by.tolkach.account.service.api.IValidationService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Primary
public class OperationDecoratorService implements IOperationService {

    private final IValidationService<Operation> operationValidationService;
    private final OperationService operationService;
    private final IBalanceService balanceService;
    private final IAccountService accountService;

    public OperationDecoratorService(IValidationService<Operation> operationValidationService,
                                     OperationService operationService,
                                     IBalanceService balanceService,
                                     IAccountService accountService) {
        this.operationValidationService = operationValidationService;
        this.operationService = operationService;
        this.balanceService = balanceService;
        this.accountService = accountService;
    }

    @Override
    public Operation create(Operation operation, UUID accountId) {
        this.operationValidationService.validate(operation);
        this.balanceService.update(accountId, this.balanceService.read(accountId).getDtUpdate(), operation.getValue());
        return this.operationService.create(operation, accountId);
    }

    @Override
    public Page<Operation> read(UUID accountId, SimplePageable pageable) {
        return this.operationService.read(accountId, pageable);
    }

    @Override
    public Operation read(UUID accountId) {
        return this.operationService.read(accountId);
    }

    @Override
    public Operation update(UUID accountId, UUID operationId, LocalDateTime dtUpdate, Operation operation) {
        this.balanceService.update(accountId, dtUpdate, operation.getValue());
        this.accountService.update(accountId, dtUpdate, this.accountService.read(accountId));
        return this.operationService.update(accountId, operationId, dtUpdate, operation);
    }
}
