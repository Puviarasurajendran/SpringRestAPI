package com.zoho.spring.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<Age, Integer> {

	private int lower;
	private int upper;

	@Override
	public void initialize(Age age) {
		System.out.println("Inside Spring Annotation Age validation initialization");
		this.lower=age.lower();
		this.upper=age.upper();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {

		if(value ==null || (value<lower || value>upper)) {
			System.out.println("Inside Spring Annotation Age validation isValid ");
			return false;
		}
		return true;
	}

}
