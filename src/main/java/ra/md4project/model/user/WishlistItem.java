package ra.md4project.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.md4project.model.product.Product;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wishlistItemId;
    @ManyToOne
    @JoinColumn(name = "wishlistId")
    private Wishlist wishlist;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
}
