package dev.abhijeet.productService.services;

import dev.abhijeet.productService.dtos.CreateProductRequestDto;
import dev.abhijeet.productService.models.Category;
import dev.abhijeet.productService.models.Product;

import java.util.List;

public interface  ProductService {
    public Product getSingleProduct(Long id);
    public Product createProduct(String title, String image, String description,
                              String category,double price);
    public List<Product> getAllProducts();
    public List<Category> getAllCategory();

    public void deleteProduct(Long id);
    public  Product updateProduct(Product product);

}
