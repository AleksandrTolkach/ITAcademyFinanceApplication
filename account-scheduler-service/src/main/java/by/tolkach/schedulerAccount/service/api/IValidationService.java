package by.tolkach.schedulerAccount.service.api;

public interface IValidationService<T> {
    T validate(T item);
}
