package ra.md4project.validate.User.PasswordMatch;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = PasswordMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PasswordMatch {
    String password();
    String confirmPassword();
    String message() default "Confirm Password do not match";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};
}
