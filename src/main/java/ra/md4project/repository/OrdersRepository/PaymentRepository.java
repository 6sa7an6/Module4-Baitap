package ra.md4project.repository.OrdersRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.md4project.model.order.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
