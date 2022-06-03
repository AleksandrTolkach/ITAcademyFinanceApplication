package by.tolkach.bot.dao;

import by.tolkach.bot.dao.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOperationStorage extends JpaRepository<OperationEntity, UUID> {
}
