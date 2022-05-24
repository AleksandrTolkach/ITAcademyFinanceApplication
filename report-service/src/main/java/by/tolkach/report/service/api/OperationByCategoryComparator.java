package by.tolkach.report.service.api;

import by.tolkach.report.dto.operation.Operation;

import java.util.Comparator;

public class OperationByCategoryComparator implements Comparator<Operation> {
    @Override
    public int compare(Operation o1, Operation o2) {
        if (o1.getCategory().equals(o2.getCategory())) {
            return 0;
        } else {
            return 1;
        }
    }
}
