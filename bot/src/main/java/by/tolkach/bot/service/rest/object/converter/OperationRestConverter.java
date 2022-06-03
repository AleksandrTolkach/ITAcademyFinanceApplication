package by.tolkach.bot.service.rest.object.converter;

import by.tolkach.bot.dto.Operation;
import by.tolkach.bot.service.rest.object.OperationRestObject;
import org.springframework.stereotype.Component;

@Component
public class OperationRestConverter implements IRestObjectConverter<Operation, OperationRestObject> {
    @Override
    public OperationRestObject toRestObject(Operation operation) {
        return OperationRestObject.Builder.createBuilder()
                .setUuid(operation.getUuid())
                .setDtUpdate(operation.getDtUpdate())
                .setDtCreate(operation.getDtCreate())
                .setCategory(operation.getCategory())
                .setAccount(operation.getAccount())
                .setDescription(operation.getDescription())
                .setValue(operation.getValue())
                .setCurrency(operation.getCurrency())
                .build();
    }

    @Override
    public Operation toDto(OperationRestObject restObject) {
        return Operation.Builder.createBuilder()
                .setUuid(restObject.getUuid())
                .setDtUpdate(restObject.getDtUpdate())
                .setDtCreate(restObject.getDtCreate())
                .setCategory(restObject.getCategory())
                .setDescription(restObject.getDescription())
                .setValue(restObject.getValue())
                .setAccount(restObject.getAccount())
                .setCurrency(restObject.getCurrency())
                .build();
    }
}
