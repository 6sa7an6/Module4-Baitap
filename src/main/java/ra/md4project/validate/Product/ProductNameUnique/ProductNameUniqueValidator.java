package ra.md4project.validate.Product.ProductNameUnique;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ra.md4project.dto.user.AuthencationUser;
import ra.md4project.service.ProductService.IProductService;

@Component
public class ProductNameUniqueValidator implements ConstraintValidator<ProductNameUnique,String> {
    @Autowired
    private IProductService productService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !productService.checkProductExists(value);
    }
}
