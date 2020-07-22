package com.blog.newsangblog2.common.validator;

import com.blog.newsangblog2.common.annotation.NotDuplication;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DuplicationValidator implements ConstraintValidator<NotDuplication, String> {



    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }

    @Override
    public void initialize(NotDuplication constraintAnnotation) {

    }
}
