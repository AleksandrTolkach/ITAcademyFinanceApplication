package by.tolkach.classifier.dao.api;

import by.tolkach.classifier.dao.api.entity.OperationCategoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IOperationCategoryStorage extends PagingAndSortingRepository<OperationCategoryEntity, UUID> {
    List<OperationCategoryEntity> findAllBy(Pageable pageable);
}
