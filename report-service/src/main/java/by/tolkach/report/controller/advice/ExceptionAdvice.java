package by.tolkach.report.controller.advice;

import by.tolkach.report.service.api.exception.MultipleErrorsException;
import by.tolkach.report.service.api.exception.NotFoundError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MultipleErrorsException.class)
    public ResponseEntity<MultipleErrorsException> multipleErrorsHandler(MultipleErrorsException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundError.class)
    public ResponseEntity<NotFoundError> notFoundErrorHandler(NotFoundError e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<NotFoundError> httpMessageReadableHandler(HttpMessageConversionException e) {
        return new ResponseEntity<>(new NotFoundError("Переданы невереные значения в тело."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<NotFoundError> methodArgumentTypeMismatchHandler(MethodArgumentTypeMismatchException e) {
        return new ResponseEntity<>(new NotFoundError("Переданы неверные параметры"), HttpStatus.BAD_REQUEST);
    }
}
