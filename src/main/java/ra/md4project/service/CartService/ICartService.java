package ra.md4project.service.CartService;

import ra.md4project.model.user.Cart;

public interface ICartService {
    void save(Cart cart);
    void delete(Cart cart);

}
