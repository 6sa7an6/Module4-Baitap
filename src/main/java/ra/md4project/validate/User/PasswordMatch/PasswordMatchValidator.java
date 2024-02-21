package ra.md4project.validate.User.PasswordMatch;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;


@Component
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch,Object> {
    private String password;
    private String confirmPassword;
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(password);
        Object confirmPasswordValue = new BeanWrapperImpl(value).getPropertyValue(confirmPassword);
        if(!passwordValue.equals(confirmPasswordValue)){
            //xet loi nay nam o field nao
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addPropertyNode(confirmPassword).addConstraintViolation();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(PasswordMatch passwordMatch) {
        this.password = passwordMatch.password();
        this.confirmPassword = passwordMatch.confirmPassword();
    }
}
