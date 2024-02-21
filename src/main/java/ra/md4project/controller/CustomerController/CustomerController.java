package ra.md4project.controller.CustomerController;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.md4project.dto.user.AuthencationUser;
import ra.md4project.dto.user.UserDto;
import ra.md4project.model.order.Orders;
import ra.md4project.model.order.OrdersItem;
import ra.md4project.model.order.Payment;
import ra.md4project.model.product.Product;
import ra.md4project.model.user.Cart;
import ra.md4project.model.user.CartItem;
import ra.md4project.model.user.User;
import ra.md4project.repository.AccountRepository.UserRepository;
import ra.md4project.service.CartService.ICartItemService;
import ra.md4project.service.CartService.ICartService;
import ra.md4project.service.OrdersService.IOrdersItemService;
import ra.md4project.service.OrdersService.IOrdersService;
import ra.md4project.service.OrdersService.IPaymentService;
import ra.md4project.service.ProductService.IProductService;
import ra.md4project.service.UserService.IUserService;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final IProductService productService;
    private final ICartService cartService;
    private final IUserService userService;
    private final ICartItemService cartItemService;
    private final ModelMapper modelMapper;
    private final IOrdersItemService ordersItemService;
    private final IOrdersService ordersService;
    private final IPaymentService paymentService;
    private final UserRepository userRepository;
    @GetMapping("/product")
    public String customerView(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, Model model , @RequestParam(required = false) String search) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof AuthencationUser) {
            User loggedInUser = userService.findUserById(((AuthencationUser) authentication.getPrincipal()).getUserId());
            model.addAttribute("loggedInUser" , loggedInUser);
        }
        Page<Product> productPage;
        if(search == null) {
            productPage = productService.findProductByStatusIsTrueAndCategoryStatusIsTrue(PageRequest.of(page, size));
        }else {
            productPage = productService.findProductByStatusIsTrueAndCategoryStatusIsTrueByNameContains(PageRequest.of(page, size) , search);
            model.addAttribute("search",search);
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("productList", productPage.getContent());
        return "customer/product";
    }

    // add cart
    @GetMapping("/addToCart")
    public String addToCart(@RequestParam Integer productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof AuthencationUser) {
            User user = userService.findUserById(((AuthencationUser) authentication.getPrincipal()).getUserId());
            CartItem cartItem = user.getCart().getCartItemList().stream().filter(cartItemfind -> cartItemfind.getProduct().getProductId() == productId).findFirst().orElse(null);
            if(cartItem == null){
                cartItem = new CartItem();
                cartItem.setCart(user.getCart());
                cartItem.setQuantity(1);
                cartItem.setProduct(productService.findById(productId));
            }else {
                if(cartItem.getQuantity()>=productService.findById(productId).getStock()){
                    cartItem.setQuantity(productService.findById(productId).getStock());
                }else {
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                }
            }
            cartItemService.save(cartItem);
        }
        return "redirect:/product";
    }

    //cart
    @GetMapping("/customer/cart")
    public String cartView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof AuthencationUser) {
            User user = userService.findUserById(((AuthencationUser) authentication.getPrincipal()).getUserId());
            Cart cart = user.getCart();
            model.addAttribute("cart", cart);
        }
        return "/customer/cart";
    }

    //wishlist
    @GetMapping("/customer/wishlist")
    public String wishListView() {
        return "/customer/wishlist";
    }

    //userInfor
    @GetMapping("/customer/userInfor")
    public String userInfor(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof AuthencationUser) {
            User loggedInUser = userService.findUserById(((AuthencationUser) authentication.getPrincipal()).getUserId());
            model.addAttribute("loggedInUser" , loggedInUser);
        }
        return "/customer/userInfor";
    }

    // Change Quantity
    @GetMapping("/customer/cart/changeQuantity")
    public String changeQuantity(@RequestParam Boolean change , @RequestParam Integer id){
        CartItem cartItem = cartItemService.findCartItemById(id);
        Product product = productService.findById(cartItem.getProduct().getProductId());
        Integer quantity = cartItem.getQuantity();
        if(change){
            if(quantity == product.getStock()){
                cartItem.setQuantity(product.getStock());
            }else {
            cartItem.setQuantity(quantity+1);
            }
            cartItemService.save(cartItem);
        }else {
            if(quantity <= 1){
                cartItemService.delete(cartItem);
            }else {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItemService.save(cartItem);
            }
        }
        return "redirect:/customer/cart";
    }

    // Orders
    @GetMapping("/customer/orders")
    public String Orders( Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof AuthencationUser) {
            User loggedInUser = userService.findUserById(((AuthencationUser) authentication.getPrincipal()).getUserId());
            model.addAttribute("loggedInUser" , loggedInUser);
        }
        return "customer/orders";
    }
    @PostMapping("/customer/orders")
    public String checkOut(@ModelAttribute("payment")Payment payment){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof AuthencationUser) {
            User user = userService.findUserById(((AuthencationUser) authentication.getPrincipal()).getUserId());
            Cart cart = user.getCart();
            List<CartItem> cartItemList= cart.getCartItemList();
            Orders orders = new Orders();
            BigDecimal totalOrders = BigDecimal.ZERO;
            List<OrdersItem> ordersItemList = new ArrayList<>();
            List<CartItem> itemsToDelete = new ArrayList<>();
            for (CartItem cartItem : cartItemList) {
                OrdersItem ordersItem = new OrdersItem();
                ordersItem.setProduct(cartItem.getProduct());
                ordersItem.setOrders(orders);
                ordersItem.setQuantity(cartItem.getQuantity());
                ordersItemList.add(ordersItem);
                totalOrders = totalOrders.add(ordersItem.getProduct().getUnitPrice().multiply(new BigDecimal(ordersItem.getQuantity())));
                itemsToDelete.add(cartItem);
            }

            // save payment
            payment.setUser(user);
            Payment paymentSave = paymentService.save(payment);
            //save order
            orders.setFullName(payment.getFullName());
            orders.setOrdersItemList(ordersItemList);
            orders.setAddressOrders(payment.getAddressOrders());
            orders.setOrderAt(new Date());
            orders.setPayment(paymentSave);
            orders.setOrderStatus(Orders.OrderStatus.WAITING);
            orders.setTotal(totalOrders);
            orders.setUser(user);
            Orders orders1= ordersService.save(orders);

            // Xóa hết cartitem đi
            cart.getCartItemList().clear();
            cartService.save(cart);

            // save user
            user.setCart(cart);
            userRepository.save(user);
            return "redirect:/customer/order/success/" + orders1.getOrderId();
        }
        return "customer/thankyou?orderId";
    }
    @GetMapping("/customer/order/success/{orderId}")
    public String orderSuccess(@PathVariable Integer orderId , Model model){
        Orders orders = ordersService.findOrdersById(orderId);
        model.addAttribute("orders" , orders);
        return "customer/thankyou";
    }

    // orders History
}
