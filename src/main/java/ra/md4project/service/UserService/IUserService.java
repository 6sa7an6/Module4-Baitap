package ra.md4project.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import ra.md4project.dto.user.UserDto;
import ra.md4project.model.user.User;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<User> findAll();
    void save(UserDto customerDto);
    User findUserById(Integer id);
    User findUserByName(String name);
    Boolean checkPhoneUnique(String phone , Integer id);
    Boolean checkUsernameUnique(String name ,  Integer id);

    Boolean checkEmailUnique(String email , Integer id);
}
