package by.tolkach.bot.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class OperationCategory extends Essence{


    private String title;

    public OperationCategory() {
    }

    public OperationCategory(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String title) {
        super(uuid, dtCreate, dtUpdate);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class Builder {

        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private String title;

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

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public OperationCategory build() {
            return new OperationCategory(uuid, dtCreate, dtUpdate, title);
        }
    }
}
