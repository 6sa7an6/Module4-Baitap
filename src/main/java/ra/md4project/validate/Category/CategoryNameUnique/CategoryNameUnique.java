package ra.md4project.validate.Category.CategoryNameUnique;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ra.md4project.validate.Product.ProductNameUnique.ProductNameUniqueValidator;

import java.lang.annotation.*;

@Constraint(validatedBy = CategoryNameUniqueValidator.class)
@Target({ElementType.FIELD , ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CategoryNameUnique {
    String message() default "CategoryName is exist";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};
}
