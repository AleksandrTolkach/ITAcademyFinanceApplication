package by.tolkach.mailScheduler.service.scheduledMail.api;

import by.tolkach.mailScheduler.dto.scheduledMail.Param;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;

import java.util.UUID;

public interface IParamService {
    Param create(Param param, ReportType reportType);
    Param read(UUID paramId);
    Param update(UUID paramId, Param param);
}
