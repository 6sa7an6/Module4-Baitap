package ra.md4project.service.ProductService;



import ra.md4project.dto.product.CategoryDto;
import ra.md4project.model.product.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    void save(CategoryDto categoryDto);
    Category findById(Integer id);
    Boolean isCategoryExists(String categoryName);
}
