package ra.md4project.validate.User.UsernameUnique;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = UsernameUniqueValidator.class)
@Target({ElementType.METHOD , ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UsernameUnique {
    String message() default "User Name is exist";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};
}
