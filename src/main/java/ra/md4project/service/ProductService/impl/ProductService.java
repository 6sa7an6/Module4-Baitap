package ra.md4project.service.ProductService.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.md4project.dto.product.ProductDto;
import ra.md4project.model.product.Product;
import ra.md4project.repository.ProductRepository.ProductRepository;
import ra.md4project.service.ProductService.IProductService;
import ra.md4project.service.UploadService;

import java.util.Date;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(ProductDto productDto) {
        String productUrl = null;
        if(productDto.getProductId()!= null){
            productUrl = productRepository.findById(productDto.getProductId()).get().getProductUrl();
        }
        if(productDto.getProductFile() != null && productDto.getProductFile().getSize()>0){
            productUrl = uploadService.uploadFileToServer(productDto.getProductFile());
        }
        Product product = modelMapper.map(productDto , Product.class);
        product.setStatus(true);
        product.setProductUrl(productUrl);
        productRepository.save(product);
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean checkProductExists(String productName) {
        return productRepository.existsByProductName(productName);
    }

    @Override
    public Page<Product> findProductByStatusIsTrueAndCategoryStatusIsTrue(Pageable pageable) {
        return productRepository.findProductByStatusIsTrueAndCategoryStatusIsTrue(pageable);
    }

    @Override
    public Page<Product> findProductByStatusIsTrueAndCategoryStatusIsTrueByNameContains(Pageable pageable, String name) {
        return productRepository.findProductByStatusIsTrueAndCategoryStatusIsTrueByNameContains(pageable,name);
    }
}
