package by.tolkach.mailScheduler.controller.converter;

import by.tolkach.mailScheduler.dto.exception.NotFoundException;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;
import org.springframework.core.convert.converter.Converter;

public class StringToReportTypeConverter implements Converter<String, ReportType> {
    @Override
    public ReportType convert(String source) {
        try {
            ReportType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Указан неверный тип отчета.");
        }
        return ReportType.valueOf(source.toUpperCase());
    }
}
