package by.tolkach.bot.service.rest.object.converter;

public interface IRestObjectConverter<DTO, REST> {
    REST toRestObject(DTO dto);
    DTO toDto(REST restObject);
}
