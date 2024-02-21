package ra.md4project.validate.Product.ProductNameUnique;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ProductNameUniqueValidator.class)
@Target({ElementType.FIELD , ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductNameUnique {
    String message() default "ProductName is exist";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};
}
