package ra.md4project.controller.LoginController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.md4project.dto.user.UserDto;
import ra.md4project.model.product.Category;
import ra.md4project.model.user.User;
import ra.md4project.service.ProductService.ICategoryService;
import ra.md4project.service.UserService.IUserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final IUserService userService;
    @GetMapping(value = "/save")
    public String register(@RequestParam(required = false)Integer id, Model model){
        User user = new User();
        if(id!=null) {
        user = userService.findUserById(id);
        }
        model.addAttribute("user" , user);
        return "login/save";
    }
    @PostMapping("/save")
    public String accountSave(@ModelAttribute("user") @Valid UserDto userDto , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/login/save";
        }
        userService.save(userDto);
        return "redirect:/home";
    }
    @GetMapping("/login")
    public String login(){
        return "login/login";
    }
}
