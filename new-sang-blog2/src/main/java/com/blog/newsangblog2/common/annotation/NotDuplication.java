package com.blog.newsangblog2.common.annotation;


import com.blog.newsangblog2.common.validator.DuplicationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = { DuplicationValidator.class })
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface NotDuplication {

    String message() default "이미 존재 합니다.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
