package ra.md4project.service.UserService.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ra.md4project.dto.user.AuthencationUser;
import ra.md4project.dto.user.UserDto;
import ra.md4project.model.user.Cart;
import ra.md4project.model.user.Role;
import ra.md4project.model.user.User;
import ra.md4project.repository.AccountRepository.UserRepository;
import ra.md4project.service.CartService.ICartService;
import ra.md4project.service.UserService.IUserService;
import ra.md4project.service.UserService.IRoleServicer;
import ra.md4project.service.UploadService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UploadService uploadService;
    private final ModelMapper modelMapper;
    private final IRoleServicer roleServicer;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ICartService cartService;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(UserDto userDto) {
        User user;
        Cart cart = new Cart();
        String avatarUrl = null;
        if(userDto.getUserId()!= null){
            user = findUserById(userDto.getUserId());
            avatarUrl = user.getAvatarUrl();
            cart = user.getCart();
        }
        if(userDto.getUserFile() != null && userDto.getUserFile().getSize()>0){
            avatarUrl = uploadService.uploadFileToServer(userDto.getUserFile());
        }
        user = modelMapper.map(userDto,User.class);
        user.setRole(roleServicer.findRoleByName("USER"));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setStatusAccount(true);
        user.setAvatarUrl(avatarUrl);
        user.setCart(cart);
        userRepository.save(user);
        cart.setUser(user);
        cartService.save(cart);
    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findUserByName(name).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByName(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        User user = userOptional.get();
        return new AuthencationUser(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(Arrays.asList(user.getRole())),
                user.getUserId()
        );
    }
    private List<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean checkPhoneUnique(String phone , Integer id) {
        return userRepository.existsByPhoneNumberAndUserIdIsNot(phone , id).isPresent();
    }

    @Override
    public Boolean checkUsernameUnique(String name , Integer id) {
        return userRepository.existsByUsernameAndUserIdIsNot(name, id).isPresent();
    }

    @Override
    public Boolean checkEmailUnique(String email, Integer id) {
        return userRepository.existsByEmailAndUserIdIsNot(email,id).isPresent();
    }
}
