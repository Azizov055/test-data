package com.example.data.validator;

import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PublishedValidator implements ConstraintValidator<Published, LocalDate> {

  @Override
  public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
    return value.isBefore(LocalDate.now());
  }

}
