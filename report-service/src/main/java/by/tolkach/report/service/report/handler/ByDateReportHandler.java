package by.tolkach.report.service.report.handler;

import by.tolkach.report.dto.operation.Operation;
import by.tolkach.report.dto.report.param.Param;
import by.tolkach.report.service.api.IOperationService;
import by.tolkach.report.service.report.handler.api.IReportHandler;
import by.tolkach.report.service.report.api.OperationByDateComparator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ByDateReportHandler implements IReportHandler {

    private final IOperationService operationService;

    public ByDateReportHandler(IOperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public List<Operation> getOperations(Param param) {
        List<Operation> operations = this.operationService.read(param);
        List<Operation> filteredOperations = new ArrayList<>();
        for (Operation operation: operations) {
            if (isInInterval(param, operation)) {
                filteredOperations.add(operation);
            }
        }
        filteredOperations.sort(new OperationByDateComparator());
        return filteredOperations;
    }

    public boolean isInInterval(Param param, Operation operation) {
        return operation.getDate().compareTo(param.getFrom()) > 0 && operation.getDate().compareTo(param.getTo()) < 0;
    }
}
