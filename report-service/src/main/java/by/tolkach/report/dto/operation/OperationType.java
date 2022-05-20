package by.tolkach.report.dto.operation;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum OperationType {
    SPEND,
    RECEIVE,
    @JsonEnumDefaultValue UNKNOWN
}
