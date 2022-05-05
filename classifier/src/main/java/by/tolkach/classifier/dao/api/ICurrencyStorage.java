package by.tolkach.classifier.dao.api;

import by.tolkach.classifier.dao.api.entity.CurrencyEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ICurrencyStorage extends PagingAndSortingRepository<CurrencyEntity, UUID> {
    List<CurrencyEntity> findAllBy(Pageable pageable);
}
