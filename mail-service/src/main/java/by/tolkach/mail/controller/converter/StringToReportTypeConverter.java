package by.tolkach.mail.controller.converter;

import by.tolkach.mail.dto.ReportType;
import by.tolkach.mail.dto.exception.NotFoundException;
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
