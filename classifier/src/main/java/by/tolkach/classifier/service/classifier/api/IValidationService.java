package by.tolkach.classifier.service.classifier.api;

public interface IValidationService<T> {
    T validate(T item);
}
