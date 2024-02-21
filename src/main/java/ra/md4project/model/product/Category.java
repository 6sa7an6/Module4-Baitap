package ra.md4project.model.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.md4project.validate.Category.CategoryNameUnique.CategoryNameUnique;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(unique = true,length = 100)
    private String categoryName;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "bit")
    private Boolean status;
}
