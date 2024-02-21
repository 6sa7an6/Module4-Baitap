package ra.demospringmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ra.demospringmvc.model.Post;
import ra.demospringmvc.service.IPostService;

@Controller
public class PostController {
    @Autowired
    private IPostService postService;
    @RequestMapping("/home")
    public String home(Model model){
        // lấy ra danh sách bài đăng và gửi nó đến view
        model.addAttribute("list" , postService.findAllPost());
        return "home";
    }
    @RequestMapping(value = "/search" , method = RequestMethod.GET)
    public String search(@RequestParam String search, Model model){
        model.addAttribute("list" , postService.findAllPostByTitleOrContent(search));
        model.addAttribute("search",search);
        return "home";
    }
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String delete(@RequestParam Integer id){
        postService.delete(id);
        return "redirect:/home";
    }
    @RequestMapping(value = "/save" , method = RequestMethod.GET)
    public String save(@RequestParam(required = false) Integer id,Model model){
        Post post = new Post();
        if(id != null){
            post = postService.findPostById(id);
        }
        model.addAttribute(post);
        return "save";
    }
    @RequestMapping(value = "/save" , method = RequestMethod.POST)
    public String save(@ModelAttribute Post post){
        postService.save(post);
        return "redirect:/home";
    }
}
