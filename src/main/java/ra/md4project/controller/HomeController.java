package ra.md4project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.md4project.dto.user.AuthencationUser;
import ra.md4project.model.user.User;
import ra.md4project.service.UserService.IUserService;

@Controller
public class HomeController {
    @Autowired
    private IUserService userService;
    @GetMapping({"/","/home"})
    public String home(Model model){
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof AuthencationUser) {
                User loggedInUser = userService.findUserById(((AuthencationUser) principal).getUserId());
                model.addAttribute("loggedInUser" , loggedInUser);
            }
        }
        return "home";
    }
}
