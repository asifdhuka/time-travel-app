package com.gamesys.timetravel.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {TimeTravelMessageValidator.class})
public @interface ValidTimeTravelMessage {
    String message() default "Invalid request";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
