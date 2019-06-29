package com.megatravel.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidation {
	
	String message() default "Potrebno je uneti validnu e-mail adresu";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

}
