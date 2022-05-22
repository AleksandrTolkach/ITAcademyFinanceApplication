package by.tolkach.report.dto.report;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum ReportType {

    BALANCE,
    BY_DATE,
    BY_CATEGORY,
    @JsonEnumDefaultValue UNKNOWN
}
