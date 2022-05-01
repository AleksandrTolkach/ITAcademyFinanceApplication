package by.tolkach.account.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum OperationType {
    SPEND,
    RECEIVE,
    @JsonEnumDefaultValue UNKNOWN
}
