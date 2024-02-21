package ra.md4project.dto.user;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Getter
@Setter
public class AuthencationUser extends User {
    private Integer userId;

    public AuthencationUser(String username, String password, Collection<? extends GrantedAuthority> authorities , Integer userId) {
        super(username, password, authorities);
        this.userId = userId;
    }
}
