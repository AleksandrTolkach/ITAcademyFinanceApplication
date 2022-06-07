package by.tolkach.mailScheduler.service.rest.object.converter;

import by.tolkach.mailScheduler.dto.scheduledMail.Param;
import by.tolkach.mailScheduler.service.rest.object.ParamRestObject;
import org.springframework.stereotype.Component;

@Component
public class ParamRestObjectConverter implements IRestObjectConverter<Param, ParamRestObject> {
    @Override
    public ParamRestObject toRestObject(Param param) {
        return ParamRestObject.Builder.createBuilder()
                .setUuid(param.getUuid())
                .setAccounts(param.getAccounts())
                .setFrom(param.getFrom())
                .setTo(param.getTo())
                .setCategories(param.getCategories())
                .build();
    }

    @Override
    public Param toDto(ParamRestObject restObject) {
        return Param.Builder.createBuilder()
                .setUuid(restObject.getUuid())
                .setAccounts(restObject.getAccounts())
                .setFrom(restObject.getFrom())
                .setTo(restObject.getTo())
                .setCategories(restObject.getCategories())
                .build();
    }
}
