package by.tolkach.bot.dao;

import by.tolkach.bot.dao.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IChatStorage extends JpaRepository<ChatEntity, UUID> {
    ChatEntity findByChatId(Long chatId);
}
