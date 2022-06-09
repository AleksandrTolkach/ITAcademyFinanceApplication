package by.tolkach.schedulerAccount.controller.advice;

import by.tolkach.schedulerAccount.dto.exception.*;
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

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundException> notFoundErrorHandler(NotFoundException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<ConversionException> httpMessageReadableHandler(HttpMessageConversionException e) {
        return new ResponseEntity<>(new ConversionException("Переданые невереные значения в тело."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<TypeMismatchException> methodArgumentTypeMismatchHandler(MethodArgumentTypeMismatchException e) {
        return new ResponseEntity<>(new TypeMismatchException("Передан неверный тип данных в параметр " + e.getName() + "."),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ScheduleException.class)
    public ResponseEntity<ScheduleException> methodArgumentTypeMismatchHandler(ScheduleException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
