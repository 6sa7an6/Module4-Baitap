package ra.md4project.service.ProductService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.md4project.dto.product.ProductDto;
import ra.md4project.model.product.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    void save(ProductDto productDto);
    Product findById(Integer id);
    Boolean checkProductExists(String productName);
    Page<Product> findProductByStatusIsTrueAndCategoryStatusIsTrue(Pageable pageable);
    Page<Product> findProductByStatusIsTrueAndCategoryStatusIsTrueByNameContains(Pageable pageable , String name);

}
