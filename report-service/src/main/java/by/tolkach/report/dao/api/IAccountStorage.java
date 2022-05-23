package by.tolkach.report.dao.api;

import by.tolkach.report.dao.api.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAccountStorage extends JpaRepository<AccountEntity, UUID> {
}
