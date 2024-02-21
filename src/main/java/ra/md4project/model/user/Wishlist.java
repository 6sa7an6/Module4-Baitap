package ra.md4project.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.md4project.model.product.Product;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wishlistId;
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
    @OneToMany(mappedBy = "wishlist" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishlistItem> wishlistItemList;
}
