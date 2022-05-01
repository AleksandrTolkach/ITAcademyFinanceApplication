package by.tolkach.account.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Currency {
    BYN,
    USD,
    EUR,
    OTHER,
    @JsonEnumDefaultValue UNKNOWN
}
