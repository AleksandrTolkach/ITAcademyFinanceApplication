package by.tolkach.report.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum OperationType {
    SPEND,
    RECEIVE,
    @JsonEnumDefaultValue UNKNOWN
}
