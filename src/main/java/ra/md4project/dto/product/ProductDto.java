package ra.md4project.dto.product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;
import ra.md4project.model.product.Category;
import ra.md4project.validate.Product.ProductNameUnique.ProductNameUnique;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private Integer productId;
    @NotBlank
    @ProductNameUnique
    private String productName;
    private String description;
    @Positive
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal unitPrice;
    @Positive
    private Integer stock;
    private String productUrl;
    private Boolean status;
    private MultipartFile productFile;
    private Category category;
}
