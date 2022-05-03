package by.tolkach.schedulerAccount.service.rest.object.converter;

import by.tolkach.schedulerAccount.dto.Operation;
import by.tolkach.schedulerAccount.service.rest.object.OperationRestObject;
import org.springframework.stereotype.Component;

@Component
public class OperationRestConverter implements IRestObjectConverter<Operation, OperationRestObject> {
    @Override
    public OperationRestObject toRestObject(Operation operation) {
        return OperationRestObject.Builder.createBuilder()
                .setUuid(operation.getUuid())
                .setDescription(operation.getDescription())
                .setValue(operation.getValue())
                .setCurrency(operation.getCurrency())
                .build();
    }

    @Override
    public Operation toDto(OperationRestObject restObject) {
        return Operation.Builder.createBuilder()
                .setUuid(restObject.getUuid())
                .setDescription(restObject.getDescription())
                .setValue(restObject.getValue())
                .setCurrency(restObject.getCurrency())
                .build();
    }
}
