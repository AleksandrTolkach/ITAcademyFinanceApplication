package by.tolkach.report.dao.api;

import by.tolkach.report.dao.api.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICurrencyStorage extends JpaRepository<CurrencyEntity, UUID> {
    CurrencyEntity findByTitle(String title);
}
