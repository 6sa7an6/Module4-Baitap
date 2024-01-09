package ra.crudjsp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private Long id;
    private String productName;
    private Double price;
    private String description;
    private Boolean status;
    private Integer stock;
}
