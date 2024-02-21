package ra.md4project.dto.product;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.md4project.validate.Category.CategoryNameUnique.CategoryNameUnique;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {
    private Integer categoryId;
    @NotBlank
    @CategoryNameUnique
    private String categoryName;
    private String description;
    private Boolean status;
}
