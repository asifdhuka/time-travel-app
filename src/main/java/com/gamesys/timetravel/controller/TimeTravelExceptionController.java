package com.gamesys.timetravel.controller;

import com.gamesys.timetravel.domain.exception.TimeTravelError;
import com.gamesys.timetravel.domain.exception.TimeTravelException;
import com.gamesys.timetravel.domain.exception.TimeTravelException.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class TimeTravelExceptionController {

    // Method to handle exception depending on the exception type
    @ExceptionHandler(TimeTravelException.class)
    public ResponseEntity<Object> handleException(TimeTravelException ex, WebRequest request) {
        TimeTravelError error = new TimeTravelError();
        error.setCode(ex.getErrorType().name());
        error.setReason(ex.getReason());
        error.setMessage(ex.getMessage());
        switch (ex.getErrorType()) {
            case VALIDATION:
                error.setStatus("400");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            case INTERNAL:
                error.setStatus("500");
                return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
            case NOT_FOUND:
                error.setStatus("404");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            default:
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // For all other general error
    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Object> handleRunTimeException(RuntimeException ex, HttpServletRequest request) {
        TimeTravelError error = new TimeTravelError();
        error.setCode(ErrorType.INTERNAL.name());
        error.setReason(ex.getMessage());
        error.setMessage(ex.getLocalizedMessage());
        error.setStatus("500");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
