package com.gamesys.timetravel.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TimeTravelException extends RuntimeException {
    private ErrorType errorType;
    private String reason;
    private String message;

    private TimeTravelException(ErrorType errorType, String reason, String message) {
        this.errorType = errorType;
        this.message = message;
        this.reason = reason;
    }

    public static TimeTravelException createTimeTravelException(
            ErrorType errorType, String reason, String message) {
        return new TimeTravelException(errorType, reason, message);
    }

    public enum ErrorType {
        INTERNAL,
        NOT_FOUND,
        VALIDATION,
    }
}
