package by.tolkach.schedulerAccount.controller.advice;

import by.tolkach.schedulerAccount.service.api.exception.MultipleErrorsException;
import by.tolkach.schedulerAccount.service.api.exception.NotFoundError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
