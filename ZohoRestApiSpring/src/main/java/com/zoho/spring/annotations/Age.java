package com.zoho.spring.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {AgeValidator.class})
public @interface Age {

	String message() default "Age must be within {lower} to {upper}";

	int lower() default 18;
	int upper() default 60;

	Class<?>[] groups() default{};

	Class<? extends Payload>[] payload() default{};

}
