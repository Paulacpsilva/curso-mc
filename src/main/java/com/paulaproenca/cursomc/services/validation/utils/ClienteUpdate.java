package com.paulaproenca.cursomc.services.validation.utils;

import com.paulaproenca.cursomc.services.validation.ClienteInsertValidator;
import com.paulaproenca.cursomc.services.validation.ClienteUpdateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ClienteUpdateValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface ClienteUpdate {

    String message() default  "Erro de vaidação";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
