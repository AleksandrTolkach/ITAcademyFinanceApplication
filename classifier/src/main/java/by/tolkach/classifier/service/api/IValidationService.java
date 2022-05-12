package by.tolkach.classifier.service.api;

public interface IValidationService<T> {
    T validate(T item);
}
