package by.tolkach.report.dao.api;

import by.tolkach.report.dao.api.entity.OperationCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOperationCategoryStorage extends JpaRepository<OperationCategoryEntity, UUID> {
    OperationCategoryEntity findByTitle(String title);
}
