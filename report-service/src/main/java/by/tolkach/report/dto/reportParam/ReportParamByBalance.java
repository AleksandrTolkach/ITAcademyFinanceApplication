package by.tolkach.report.dto.reportParam;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReportParamByBalance {

    private List<UUID> accounts = new ArrayList<>();

    public ReportParamByBalance() {
    }

    public ReportParamByBalance(List<UUID> accounts) {
        this.accounts = accounts;
    }

    public List<UUID> getAccounts() {
        return accounts;
    }

    public void addAccount(UUID accountId) {
        this.accounts.add(accountId);
    }
}
