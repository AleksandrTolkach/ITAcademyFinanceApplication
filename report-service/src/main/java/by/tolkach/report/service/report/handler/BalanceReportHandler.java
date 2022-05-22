package by.tolkach.report.service.report.handler;

import by.tolkach.report.dto.operation.Operation;
import by.tolkach.report.dto.report.param.Param;
import by.tolkach.report.service.api.IOperationService;
import by.tolkach.report.service.report.handler.api.IReportHandler;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BalanceReportHandler implements IReportHandler {

    private final IOperationService operationService;

    public BalanceReportHandler(IOperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public List<Operation> getOperations(Param param) {
        return this.operationService.read(param);
    }
}
