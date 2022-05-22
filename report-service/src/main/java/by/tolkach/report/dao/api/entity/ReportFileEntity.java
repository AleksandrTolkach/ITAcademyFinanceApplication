package by.tolkach.report.dao.api.entity;

import by.tolkach.report.dao.api.entity.converter.attributesConverter.BytesAttributeConverter;

import javax.persistence.*;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

@Entity
@Table(schema = "application", name = "report_files")
public class ReportFileEntity {

    @Id
    UUID uuid;
    @ManyToOne
    ReportEntity report;
    @Convert(converter = BytesAttributeConverter.class)
    ByteArrayOutputStream file;

    public ReportFileEntity() {
    }

    public ReportFileEntity(UUID uuid, ReportEntity report, ByteArrayOutputStream file) {
        this.uuid = uuid;
        this.report = report;
        this.file = file;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public ReportEntity getReport() {
        return report;
    }

    public void setReport(ReportEntity report) {
        this.report = report;
    }

    public ByteArrayOutputStream getFile() {
        return file;
    }

    public void setFile(ByteArrayOutputStream file) {
        this.file = file;
    }

    public static class Builder {

        UUID uuid;
        ReportEntity report;
        ByteArrayOutputStream file;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setReport(ReportEntity report) {
            this.report = report;
            return this;
        }

        public Builder setFile(ByteArrayOutputStream file) {
            this.file = file;
            return this;
        }

        public ReportFileEntity build() {
            return new ReportFileEntity(uuid, report, file);
        }
    }
}
