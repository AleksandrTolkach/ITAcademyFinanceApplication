package by.tolkach.account.service.rest.object.converter;

public interface IRestObjectConverter<DTO, REST> {
    REST toRestObject(DTO dto);
    DTO toDto(REST restObject);
}
