package ra.crudjsp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ra.crudjsp.dao.IProductDao;
import ra.crudjsp.dao.impl.ProductDaoImpl;
import ra.crudjsp.dto.ProductRequest;
import ra.crudjsp.model.Product;
import ra.crudjsp.service.IProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }

    @Override
    public void add(ProductRequest productRequest) {
        Product product = new Product(null,productRequest.getProductName(),productRequest.getPrice(),productRequest.getDescription(),true,productRequest.getStock());
        productDao.save(product);
    }

    @Override
    public void update(Product product) {
        productDao.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }
}
