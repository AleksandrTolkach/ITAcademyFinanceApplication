package by.tolkach.mail.service.api;

public interface IValidationService<T> {
    T validate(T item);
}
