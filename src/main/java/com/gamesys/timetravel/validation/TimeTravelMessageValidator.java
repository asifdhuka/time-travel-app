package com.gamesys.timetravel.validation;

import com.gamesys.timetravel.domain.timetravel.TimeTravelMessage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;


public class TimeTravelMessageValidator
        implements ConstraintValidator<ValidTimeTravelMessage, TimeTravelMessage> {

    @Override
    public void initialize(ValidTimeTravelMessage constraintAnnotation) {

    }

    @Override
    public boolean isValid(TimeTravelMessage timeTravel, ConstraintValidatorContext ctx) {
        return isRequestValid(timeTravel, ctx);
    }

    private boolean isRequestValid(TimeTravelMessage timeTravel,
                                   ConstraintValidatorContext ctx) {
        boolean valid = true;
        String pgi = timeTravel.getPgi();
        String place = timeTravel.getPlace();
        LocalDate date = timeTravel.getDate();
        // Request should have all values
        if (pgi.isEmpty() || place.isEmpty() || date.toString().isEmpty()) {
            ctx.buildConstraintViolationWithTemplate("Request body has empty values")
                    .addConstraintViolation();
            valid = false;
        }
        // PGI should be alphanumeric
        if (!pgi.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]+$")) {
            ctx.buildConstraintViolationWithTemplate("PGI is not having alphanumeric value")
                    .addConstraintViolation();
            valid = false;
        }
        // PGI should have length between 5 to 10
        if (pgi.length() > 10 || pgi.length() < 5) {
            ctx.buildConstraintViolationWithTemplate("PGI is having expected length")
                    .addConstraintViolation();
            valid = false;
        }
        // PGI should start with a letter
        if (!pgi.substring(0, 1).matches("[a-zA-Z]")) {
            ctx.buildConstraintViolationWithTemplate("PGI is not starting with a letter")
                    .addConstraintViolation();
            valid = false;
        }
        return valid;
    }
}
