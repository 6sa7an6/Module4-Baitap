package ra.md4project.validate.Category.CategoryNameUnique;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ra.md4project.dto.user.AuthencationUser;
import ra.md4project.service.ProductService.ICategoryService;

import java.lang.annotation.Documented;

@Component
public class CategoryNameUniqueValidator implements ConstraintValidator<CategoryNameUnique,String> {
    @Autowired
    private ICategoryService categoryService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !categoryService.isCategoryExists(value);
    }
}
