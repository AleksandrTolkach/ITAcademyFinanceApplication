package by.tolkach.classifier.dto;

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
}
