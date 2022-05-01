package by.tolkach.account.controller.advice;

import by.tolkach.account.service.api.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ValidationException> validationHandler(ValidationException e) {
        return new ResponseEntity<>(new ValidationException(e.getLogRef(), e.getErrors()),
                HttpStatus.BAD_REQUEST);
    }
}
