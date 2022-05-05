package by.tolkach.classifier.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Currency extends Essence{

    private String title;

    public Currency() {
    }

    public Currency(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String title) {
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
