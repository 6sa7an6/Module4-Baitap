package ra.crudjsp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private String productName;
    private Double price;
    private String description;
    private Integer stock;
}
