package by.tolkach.bot.dao.entity.converter;

public interface IEntityConverter<DTO, ENT> {
    ENT toEntity(DTO dto);
    DTO toDto(ENT entity);
}
