package by.tolkach.mailScheduler.service.scheduledMail.api;

import by.tolkach.mailScheduler.dto.scheduledMail.Param;

public class Params {
    public static Param updateParameters(Param currentParam, Param newParam) {
        currentParam.setFrom(newParam.getFrom());
        currentParam.setTo(newParam.getTo());
        currentParam.setAccounts(newParam.getAccounts());
        currentParam.setCategories(newParam.getCategories());
        return currentParam;
    }
}
