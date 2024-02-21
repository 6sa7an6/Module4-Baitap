package ra.md4project.repository.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.md4project.dto.product.ProductDto;
import ra.md4project.model.product.Product;
import ra.md4project.model.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Boolean existsByProductName(String productName);
    @Query("SELECT P FROM Product P where P.status = true and P.category.status = true")
    Page<Product> findProductByStatusIsTrueAndCategoryStatusIsTrue(Pageable pageable);

    @Query("SELECT P FROM Product P where P.status = true and P.category.status = true and P.productName like %:search%")
    Page<Product> findProductByStatusIsTrueAndCategoryStatusIsTrueByNameContains(Pageable pageable , String search);
}
