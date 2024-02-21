package ra.md4project.dto.user;

import com.google.type.DateTime;
import com.google.type.Decimal;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ra.md4project.model.user.Role;
import ra.md4project.validate.User.EmailUnique.EmailUnique;
import ra.md4project.validate.User.PasswordMatch.PasswordMatch;
import ra.md4project.validate.User.PhoneUnique.PhoneUnique;
import ra.md4project.validate.User.UsernameUnique.UsernameUnique;

@AllArgsConstructor
@NoArgsConstructor
@Data
@PasswordMatch(password = "password" , confirmPassword = "confirmPassword")
public class UserDto {
    private Integer userId;
    @NotBlank
    @Size(min=6,message = "Tối thiểu 6 ký tự")
    @UsernameUnique
    private String username;
    @NotBlank
    @Size(min = 5,message = "Tối thiểu 5 ký tự")
    @Pattern(regexp = "^[A-Z].+$")
    private String password;
    private String confirmPassword;
    @NotBlank
    @Email(message = "Địa chỉ Email không đúng định dạng")
    @EmailUnique
    private String email;
    @NotBlank
    @Pattern(regexp = "^[0-9]{10,11}$" , message = "Số điện thoại không đúng định dạng")
    @PhoneUnique
    private String phoneNumber;
    private String address;
    private String avatarUrl;
    private MultipartFile userFile;
    private Role role;
}
