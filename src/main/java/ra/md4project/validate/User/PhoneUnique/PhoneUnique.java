package ra.md4project.validate.User.PhoneUnique;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = PhoneUniqueValidator.class)
@Target({ElementType.METHOD , ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PhoneUnique {
    String message() default "Phone is exist";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};
}
