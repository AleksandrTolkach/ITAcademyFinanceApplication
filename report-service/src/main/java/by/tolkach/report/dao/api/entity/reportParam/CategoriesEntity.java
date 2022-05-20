package by.tolkach.report.dao.api.entity.reportParam;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class CategoriesEntity implements Serializable {

    private List<UUID> categories;

    public CategoriesEntity() {
    }

    public CategoriesEntity(List<UUID> categories) {
        this.categories = categories;
    }

    public List<UUID> getCategories() {
        return categories;
    }

    public void setCategories(List<UUID> categories) {
        this.categories = categories;
    }
}
