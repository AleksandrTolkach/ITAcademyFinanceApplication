package by.tolkach.report.service.report.api;

import by.tolkach.report.dto.operation.Operation;

import java.util.Comparator;

public class OperationByCategoryComparator implements Comparator<Operation> {
    @Override
    public int compare(Operation o1, Operation o2) {
        switch (o1.getCategory().compareTo(o2.getCategory())) {
            case -1:
                return -1;
            case 1:
                return 1;
        }
        return 0;
    }
}
