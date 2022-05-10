package by.tolkach.report.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum ReportStatus {

    LOADED,
    PROGRESS,
    ERROR,
    DONE,
    @JsonEnumDefaultValue UNKNOWN
}
