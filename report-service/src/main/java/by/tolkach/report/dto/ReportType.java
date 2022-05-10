package by.tolkach.report.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum ReportType {

    BALANCE,
    BY_DATE,
    BY_CATEGORY,
    @JsonEnumDefaultValue UNKNOWN
}
