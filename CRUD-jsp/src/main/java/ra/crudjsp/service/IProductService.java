package ra.crudjsp.service;

import ra.crudjsp.dto.ProductRequest;
import ra.crudjsp.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findById(Long id);
    void add(ProductRequest productRequest);
    void update(Product product);

    void deleteById(Long id);
}
