package ra.crudjsp.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ra.crudjsp.dao.IProductDao;
import ra.crudjsp.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductDaoImpl implements IProductDao {
    private static List<Product> productList = new ArrayList<>();
    static {
        Product p1 = new Product(1L,"Quan bo",1000D,"Quan chat lieu tot",true,11);
        Product p2 = new Product(2L,"Ao so mi",500D,"Ao mac mat",true,15);
        Product p3 = new Product(3L,"Giay da",700D,"Giay le phuc",true,5);
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
    }
    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public Product findById(Long id) {
        return productList.stream().filter(product -> Objects.equals(product.getId(), id)).findAny().orElse(null);
    }

    @Override
    public void save(Product product) {
        if(product.getId()==null){
            product.setId(getNewId());
            productList.add(product);
        }else {
            productList.set(productList.indexOf(findById(product.getId())),product);
        }
    }

    @Override
    public void deleteById(Long id) {
        productList.remove(findById(id));
    }

    public Long getNewId() {
        long idMax = 0;
        for(int i = 0 ; i < productList.size();i++){
            if(idMax < productList.get(i).getId()){
                idMax = productList.get(i).getId();
            }
        }
        return idMax+1;
    }
}
