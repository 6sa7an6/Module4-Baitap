package ra.md4project.service.CartService;

import ra.md4project.model.user.CartItem;

public interface ICartItemService {
    void save(CartItem cartItem);
    CartItem findCartItemById(Integer id);
    void delete(CartItem cartItem);
}
