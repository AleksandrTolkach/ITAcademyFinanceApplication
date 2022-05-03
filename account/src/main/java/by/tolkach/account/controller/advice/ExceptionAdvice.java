package by.tolkach.account.controller.advice;

import by.tolkach.account.service.api.exception.MultipleErrorsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MultipleErrorsException.class)
    public ResponseEntity<MultipleErrorsException> multipleErrorsHandler(MultipleErrorsException e) {
        return new ResponseEntity<>(new MultipleErrorsException(e.getLogRef(), e.getErrors()),
                HttpStatus.BAD_REQUEST);
    }
}
