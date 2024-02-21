package ra.md4project.service.CartService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.md4project.model.user.CartItem;
import ra.md4project.repository.OrdersRepository.CartItemRepository;
import ra.md4project.service.CartService.ICartItemService;

@Service
public class CartItemService implements ICartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem findCartItemById(Integer id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }
}
