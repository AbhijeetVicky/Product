package dev.abhijeet.productService.services;

import dev.abhijeet.productService.models.Category;
import dev.abhijeet.productService.models.Product;
import dev.abhijeet.productService.repositotries.CategoryRepository;
import dev.abhijeet.productService.repositotries.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("selfProductService")
public class SelfProductService implements ProductService{
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    public  SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) {
        return productRepository.findByIdIs(id);

    }

    @Override
    public Product createProduct(String title, String image, String description, String categoryTitle, double price) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageURL(image);

        Category categoryFromDB =  categoryRepository.findByTitle(categoryTitle);
        if(categoryFromDB  == null){
            Category newCategory = new Category();
            newCategory.setTitle(categoryTitle);
            categoryFromDB = categoryRepository.save(newCategory);
        }

        product.setCategory(categoryFromDB);

        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return  productRepository.findAll();
    }

    @Override
    public List<Category> getAllCategory() {
        return  categoryRepository.findAll();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

}
