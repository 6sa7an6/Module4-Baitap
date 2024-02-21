package ra.md4project.service.ProductService.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.md4project.dto.product.CategoryDto;
import ra.md4project.model.product.Category;
import ra.md4project.repository.ProductRepository.CategoryRepository;
import ra.md4project.service.ProductService.ICategoryService;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(category);
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean isCategoryExists(String categoryName) {
        return categoryRepository.existsByCategoryName(categoryName);
    }

}
