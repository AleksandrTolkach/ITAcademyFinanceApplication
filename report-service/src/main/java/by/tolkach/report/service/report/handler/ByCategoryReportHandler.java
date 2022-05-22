package by.tolkach.report.service.report.handler;

import by.tolkach.report.dto.operation.Operation;
import by.tolkach.report.dto.report.param.Param;
import by.tolkach.report.service.api.IOperationService;
import by.tolkach.report.service.report.handler.api.IReportHandler;
import by.tolkach.report.service.report.api.OperationByCategoryComparator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ByCategoryReportHandler implements IReportHandler {

    private final IOperationService operationService;

    public ByCategoryReportHandler(IOperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public List<Operation> getOperations(Param param) {
        List<Operation> operations = this.operationService.read(param);
        List<Operation> filteredOperations = new ArrayList<>();
        for (Operation operation: operations) {
            for (UUID category: param.getCategories()) {
                if (operation.getCategory().compareTo(category) == 0) {
                    filteredOperations.add(operation);
                }
            }
        }
        filteredOperations.sort(new OperationByCategoryComparator());
        return filteredOperations;
    }
}
