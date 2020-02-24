package Projeto.service.validation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ClienteInsertValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

    public @interface ClienteInsert {
        String message() default "Erro de validação";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

