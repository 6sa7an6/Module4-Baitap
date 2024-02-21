package ra.md4project.model.product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @Column(unique = true , length = 100)
    private String productName;
    @Column(columnDefinition = "text")
    private String description;
    private BigDecimal unitPrice;
    private Integer stock;
    private String productUrl;
    @Column(columnDefinition = "bit")
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
