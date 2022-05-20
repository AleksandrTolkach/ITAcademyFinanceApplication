package by.tolkach.report.service.api;

import by.tolkach.report.dto.operation.Operation;

import java.util.Comparator;

public class OperationByDateComparator implements Comparator<Operation> {
    @Override
    public int compare(Operation o1, Operation o2) {
        switch (o1.getDate().compareTo(o2.getDate())) {
            case -1:
                return -1;
            case 1:
                return 1;
        }
        return 0;
    }
}
