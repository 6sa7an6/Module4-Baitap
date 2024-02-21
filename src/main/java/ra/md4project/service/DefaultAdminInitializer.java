package ra.md4project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ra.md4project.dto.user.UserDto;
import ra.md4project.model.user.Role;
import ra.md4project.model.user.User;
import ra.md4project.repository.AccountRepository.UserRepository;
import ra.md4project.service.UserService.IUserService;
import ra.md4project.service.UserService.IRoleServicer;

@Component
@RequiredArgsConstructor
public class DefaultAdminInitializer {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final IRoleServicer roleServicer;
    @EventListener(ApplicationReadyEvent.class)
    public void initializeDefaultAdmin(){
        Role roleUser = new Role("USER");
        Role roleAdmin = new Role("ADMIN");
        Role roleMod = new Role("MODERATOR");
        roleServicer.save(roleUser);
        roleServicer.save(roleAdmin);
        roleServicer.save(roleMod);
        if(userRepository.findUserByName("admin").isEmpty()){
            User admin = new User();
            admin.setUserId(1);
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            Role adminRole = roleServicer.findRoleByName("ADMIN");
            admin.setRole(adminRole);
            userRepository.save(admin);
        }
    }
}
