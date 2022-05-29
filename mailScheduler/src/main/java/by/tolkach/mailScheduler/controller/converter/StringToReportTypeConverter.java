package by.tolkach.mailScheduler.controller.converter;

import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;
import by.tolkach.mailScheduler.service.api.exception.NotFoundError;
import org.springframework.core.convert.converter.Converter;

public class StringToReportTypeConverter implements Converter<String, ReportType> {
    @Override
    public ReportType convert(String source) {
        try {
            ReportType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NotFoundError("Указан неверный тип отчета.");
        }
        return ReportType.valueOf(source.toUpperCase());
    }
}
