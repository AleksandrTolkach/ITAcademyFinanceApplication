package by.tolkach.account.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum AccountType {
    CASH,
    BANK_ACCOUNT,
    BANK_DEPOSIT,
    @JsonEnumDefaultValue UNKNOWN
}
