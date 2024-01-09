package ra.crudjsp.dao;

import ra.crudjsp.model.Product;

import java.util.List;

public interface IProductDao {
    List<Product> findAll();
    Product findById(Long id);
    void save(Product product);

    void deleteById(Long id);

}
