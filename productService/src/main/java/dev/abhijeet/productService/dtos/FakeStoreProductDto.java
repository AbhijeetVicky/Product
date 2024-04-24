package dev.abhijeet.productService.dtos;

import dev.abhijeet.productService.models.Category;
import dev.abhijeet.productService.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;

    public Product toProduct(  ){
        Product product = new Product();
        product.setId(getId());
        product.setTitle( getTitle());
        product.setDescription(getDescription());
        product.setImageURL(getImage());

        Category category = new Category();
        category.setTitle(getCategory());
        product.setCategory(category);

        return  product;
    }
}
