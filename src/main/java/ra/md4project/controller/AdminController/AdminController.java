package ra.md4project.controller.AdminController;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.md4project.dto.product.CategoryDto;
import ra.md4project.dto.product.ProductDto;
import ra.md4project.dto.user.AuthencationUser;
import ra.md4project.dto.user.UserDto;
import ra.md4project.model.order.Orders;
import ra.md4project.model.order.OrdersItem;
import ra.md4project.model.product.Category;
import ra.md4project.model.product.Product;
import ra.md4project.model.user.User;
import ra.md4project.repository.AccountRepository.UserRepository;
import ra.md4project.repository.ProductRepository.CategoryRepository;
import ra.md4project.repository.ProductRepository.ProductRepository;
import ra.md4project.service.OrdersService.impl.OrdersService;
import ra.md4project.service.ProductService.ICategoryService;
import ra.md4project.service.ProductService.IProductService;
import ra.md4project.service.UserService.IRoleServicer;
import ra.md4project.service.UserService.IUserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleServicer roleServicer;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrdersService ordersService;
//User
    @GetMapping ("/user")
    public String user(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof AuthencationUser) {
            User loggedInUser = userService.findUserById(((AuthencationUser) authentication.getPrincipal()).getUserId());
            model.addAttribute("loggedInUser" , loggedInUser);
        }
        List<User> userList = userService.findAll();
        model.addAttribute("userList",userList);
        return "/admin/user";
    }
    @GetMapping("/user/status")
    public String updateStatus(@RequestParam Boolean status ,@RequestParam Integer id){
        User user = userService.findUserById(id);
        if(status){
            user.setStatusAccount(false);
        }else {
            user.setStatusAccount(true);
        }
        userRepository.save(user);
        return "redirect:/admin/user";
    }
    @GetMapping("/user/role")
    public String setRole(@RequestParam String role , @RequestParam Integer id){
        User user = userService.findUserById(id);
        if(role.equals("USER")){
            user.setRole(roleServicer.findRoleByName("MODERATOR"));
        }else if(role.equals("MODERATOR")){
            user.setRole(roleServicer.findRoleByName("USER"));
        }
        userRepository.save(user);
        return "redirect:/admin/user";
    }


//Category
    @GetMapping("/category")
    public String category(Model model){
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "/admin/category";
    }
    @GetMapping("/category/save")
    public String saveCategoryView(@RequestParam(required = false) Integer id,Model model){
        Category category = new Category();
        if(id != null){
            category = categoryService.findById(id);
        }
        model.addAttribute("category" , category);
        return "/admin/saveCategory";
    }
    @PostMapping("/category/save")
    public String saveCategory(@ModelAttribute("category") @Valid CategoryDto categoryDto , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/admin/saveCategory";
        }
        categoryService.save(categoryDto);
        return "redirect:/admin/category";
    }
    @GetMapping("/category/status")
    public String statusCategory(@RequestParam Integer id , @RequestParam Boolean status){
        Category category = categoryService.findById(id);
        category.setStatus(!status);
        categoryRepository.save(category);
        return "redirect:/admin/category";
    }

//product
    @GetMapping("/product")
    public String product(Model model){
        List<Product> productList = productService.findAll();
        model.addAttribute("productList",productList);
        return "/admin/product";
    }
    @GetMapping(value = "/product/save")
    public String saveProductView(@RequestParam(required = false) Integer id, Model model){
        Product product = new Product();
        if(id != null){
            product = productService.findById(id);
        }
        model.addAttribute("product",product);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "/admin/saveProduct";
    }
    @PostMapping(value = "/product/save")
    public String saveProduct(@ModelAttribute("product") @Valid ProductDto productDto , BindingResult bindingResult , Model model){
        if(bindingResult.hasErrors()){
            List<Category> categoryList = categoryService.findAll();
            model.addAttribute("categoryList",categoryList);
            return "/admin/saveProduct";
        }
        productService.save(productDto);
        return "redirect:/admin/product";
    }
    @GetMapping("/product/status")
    public String statusProduct(@RequestParam Integer id , @RequestParam Boolean status){
        Product product = productService.findById(id);
        product.setStatus(!status);
        productRepository.save(product);
        return "redirect:/admin/product";
    }

    // Orders Manager
    @GetMapping("/ordersManager")
    public String orderManager(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof AuthencationUser) {
            User loggedInUser = userService.findUserById(((AuthencationUser) authentication.getPrincipal()).getUserId());
            model.addAttribute("loggedInUser" , loggedInUser);
        }
        List<Orders> ordersList = ordersService.findAll();
        model.addAttribute("ordersList" , ordersList);
        return "admin/ordersManager";
    }

    @GetMapping("/ordersManager/confirm")
    public String ordersConfirm(@RequestParam Integer ordersId){
        Orders orders = ordersService.findOrdersById(ordersId);
        orders.setOrderStatus(Orders.OrderStatus.SUCCESS);
        List<OrdersItem> ordersItemList = orders.getOrdersItemList();
        for (OrdersItem o:ordersItemList) {
            Product product = productService.findById(o.getProduct().getProductId());
            product.setStock(product.getStock() - o.getQuantity());
            productRepository.save(product);
        }
        ordersService.save(orders);
        return "redirect:/admin/ordersManager";
    }
}
