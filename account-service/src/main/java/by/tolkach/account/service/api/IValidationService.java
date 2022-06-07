package by.tolkach.account.service.api;

public interface IValidationService<T> {
    T validate(T item);
}
