package by.tolkach.report.dto.report;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum ReportStatus {

    LOADED,
    PROGRESS,
    ERROR,
    DONE,
    @JsonEnumDefaultValue UNKNOWN
}
