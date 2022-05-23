package by.tolkach.account.service.rest.object.converter;

import by.tolkach.account.dto.operation.Operation;
import by.tolkach.account.service.rest.object.OperationRestObject;
import org.springframework.stereotype.Component;

@Component
public class OperationRestConverter implements IRestObjectConverter<Operation, OperationRestObject> {
    @Override
    public OperationRestObject toRestObject(Operation operation) {
        return OperationRestObject.Builder.createBuilder()
                .setUuid(operation.getUuid())
                .setDtCreate(operation.getDtCreate())
                .setDtUpdate(operation.getDtUpdate())
                .setDate(operation.getDate())
                .setDescription(operation.getDescription())
                .setValue(operation.getValue())
                .setType(operation.getType())
                .build();
    }

    @Override
    public Operation toDto(OperationRestObject restObject) {
        return Operation.Builder.createBuilder()
                .setUuid(restObject.getUuid())
                .setDtCreate(restObject.getDtCreate())
                .setDtUpdate(restObject.getDtUpdate())
                .setDate(restObject.getDate())
                .setDescription(restObject.getDescription())
                .setValue(restObject.getValue())
                .setType(restObject.getType())
                .build();
    }
}
