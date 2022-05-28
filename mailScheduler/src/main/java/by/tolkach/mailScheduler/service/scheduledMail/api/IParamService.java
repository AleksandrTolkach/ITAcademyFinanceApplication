package by.tolkach.mailScheduler.service.scheduledMail.api;

import by.tolkach.mailScheduler.dto.scheduledMail.Param;

import java.util.UUID;

public interface IParamService {
    Param create(Param param);
    Param read(UUID paramId);
}
