package by.tolkach.mailScheduler.service.api;

public interface IValidationService<T> {
    T validate(T item);
}
