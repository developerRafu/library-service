package com.rafu.libraryservice.validators.annotations;

import java.lang.reflect.Method;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProtectlessValidator implements ConstraintValidator<Protectless, Method> {
  @Override
  public void initialize(Protectless protectless) {
    ConstraintValidator.super.initialize(protectless);
  }

  @Override
  public boolean isValid(Method method, ConstraintValidatorContext constraintValidatorContext) {
    return method != null;
  }
}
