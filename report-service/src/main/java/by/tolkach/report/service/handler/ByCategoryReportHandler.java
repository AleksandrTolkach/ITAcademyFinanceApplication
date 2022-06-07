package by.tolkach.report.service.handler;

import by.tolkach.report.dto.operation.Operation;
import by.tolkach.report.dto.report.Param;
import by.tolkach.report.service.handler.api.IReportHandler;
import by.tolkach.report.service.helper.api.IOperationService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ByCategoryReportHandler implements IReportHandler {

    private final IOperationService operationService;

    public ByCategoryReportHandler(IOperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public List<Operation> getOperations(Param param) {
        return this.operationService.readByCategory(param);
    }
}
