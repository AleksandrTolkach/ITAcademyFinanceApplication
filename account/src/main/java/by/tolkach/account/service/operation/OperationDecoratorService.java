package by.tolkach.account.service.operation;

import by.tolkach.account.dto.account.Account;
import by.tolkach.account.dto.account.Balance;
import by.tolkach.account.dto.operation.Operation;
import by.tolkach.account.dto.Page;
import by.tolkach.account.dto.SimplePageable;
import by.tolkach.account.service.account.api.IAccountService;
import by.tolkach.account.service.account.api.IBalanceService;
import by.tolkach.account.service.operation.api.IOperationService;
import by.tolkach.account.service.api.IValidationService;
import by.tolkach.account.service.rest.api.IReportRestClientService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Primary
@Transactional
public class OperationDecoratorService implements IOperationService {

    private final IValidationService<Operation> operationValidationService;
    private final OperationService operationService;
    private final IBalanceService balanceService;
    private final IAccountService accountService;
    private final IReportRestClientService reportRestClientService;

    public OperationDecoratorService(IValidationService<Operation> operationValidationService,
                                     OperationService operationService,
                                     IBalanceService balanceService,
                                     IAccountService accountService,
                                     IReportRestClientService reportRestClientService) {
        this.operationValidationService = operationValidationService;
        this.operationService = operationService;
        this.balanceService = balanceService;
        this.accountService = accountService;
        this.reportRestClientService = reportRestClientService;
    }

    @Override
    public Operation create(Operation operation, UUID accountId) {
        this.operationValidationService.validate(operation);
        this.balanceService.update(accountId, this.balanceService.read(accountId).getDtUpdate(), operation.getValue());
        Operation createdOperation = this.operationService.create(operation, accountId);
        this.reportRestClientService.sendOperation(createdOperation, accountId);
        return createdOperation;
    }

    @Override
    public Page<Operation> read(UUID accountId, SimplePageable pageable) {
        return this.operationService.read(accountId, pageable);
    }

    @Override
    public Operation read(UUID operationId, UUID accountId) {
        return this.operationService.read(operationId, accountId);
    }

    @Override
    public List<Operation> read(UUID accountId) {
        return this.operationService.read(accountId);
    }

    @Override
    public Operation update(UUID accountId, UUID operationId, LocalDateTime dtUpdate, Operation operation) {
        this.operationValidationService.validate(operation);
        Balance balance = this.balanceService.read(accountId);
        this.balanceService.update(accountId, balance.getDtUpdate(), operation.getValue());
        Account account = this.accountService.read(accountId);
        this.accountService.update(accountId, account.getDtUpdate(), account);
        Operation updatedOperation = this.operationService.update(accountId, operationId, dtUpdate, operation);
        this.reportRestClientService.updateOperation(updatedOperation, accountId);
        return updatedOperation;
    }

    @Override
    public void delete(UUID accountId, UUID operationId, LocalDateTime dtUpdate) {
        Operation operation = this.read(operationId, accountId);
        this.balanceService.update(accountId,
                this.balanceService.read(accountId).getDtUpdate(), (operation.getValue() * -1));
        this.operationService.delete(accountId, operationId, dtUpdate);
        this.reportRestClientService.deleteOperation(operationId, accountId);
    }
}
