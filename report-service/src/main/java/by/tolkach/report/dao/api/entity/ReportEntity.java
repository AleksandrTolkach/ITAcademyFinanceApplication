package by.tolkach.report.dao.api.entity;

import by.tolkach.report.dao.api.entity.reportParam.ExtendedParamEntity;
import by.tolkach.report.dto.ReportStatus;
import by.tolkach.report.dto.ReportType;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReportEntity extends EssenceEntity {

    private ReportStatus status;
    private ReportType type;
    private String description;
    private ExtendedParamEntity params;

    public ReportEntity() {
    }

    public ReportEntity(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, ReportStatus status,
                        ReportType type, String description, ExtendedParamEntity params) {
        super(uuid, dtCreate, dtUpdate);
        this.status = status;
        this.type = type;
        this.description = description;
        this.params = params;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExtendedParamEntity getParams() {
        return params;
    }

    public void setParams(ExtendedParamEntity params) {
        this.params = params;
    }

    public static class Builder {

        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private ReportStatus status;
        private ReportType type;
        private String description;
        private ExtendedParamEntity params;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setDtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public Builder setDtUpdate(LocalDateTime dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public Builder setStatus(ReportStatus status) {
            this.status = status;
            return this;
        }

        public Builder setType(ReportType type) {
            this.type = type;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setParams(ExtendedParamEntity params) {
            this.params = params;
            return this;
        }

        public ReportEntity build() {
            return new ReportEntity(uuid, dtCreate, dtUpdate, status, type, description, params);
        }
    }
}
