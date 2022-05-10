package by.tolkach.account.dao.api;

import by.tolkach.account.dao.api.entity.AccountEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAccountStorage extends PagingAndSortingRepository<AccountEntity, UUID> {
    List<AccountEntity> findAllBy(Pageable pageable);
}
