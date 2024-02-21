package ra.md4project.repository.OrdersRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.md4project.model.user.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem , Integer> {
}
