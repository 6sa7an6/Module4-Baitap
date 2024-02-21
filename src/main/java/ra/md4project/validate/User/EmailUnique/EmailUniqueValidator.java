package ra.md4project.validate.User.EmailUnique;

import jakarta.validation.ConstraintValidator;

import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ra.md4project.dto.user.AuthencationUser;
import ra.md4project.service.UserService.IUserService;


@Component
public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String> {
    @Autowired
    private IUserService userService;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof AuthencationUser){
            AuthencationUser user = (AuthencationUser) authentication.getPrincipal();
            return !userService.checkEmailUnique(value, user.getUserId());
        }
        return !userService.checkEmailUnique(value, null);
    }
}
