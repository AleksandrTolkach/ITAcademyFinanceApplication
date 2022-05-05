package by.tolkach.classifier.dao.api.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "application", name = "operation_category")
public class OperationCategoryEntity extends EssenceEntity {

    private String title;

    public OperationCategoryEntity() {
    }

    public OperationCategoryEntity(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String title) {
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
