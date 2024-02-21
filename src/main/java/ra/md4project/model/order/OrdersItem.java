package ra.md4project.model.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.md4project.model.product.Product;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrdersItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ordersItemId;
    private Integer quantity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ordersId")
    private Orders orders;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
}
