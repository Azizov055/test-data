package com.example.data.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    switch (value) {
      case "AZN":
      case "USD":
      case "EUR":
        return true;
      default:
        return false;
    }
  }

}
