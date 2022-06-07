package by.tolkach.mailScheduler.dao.api.entity.converter;

public interface IEntityConverter<DTO, ENT> {
    ENT toEntity(DTO dto);
    DTO toDto(ENT entity);
}
