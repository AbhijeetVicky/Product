package dev.abhijeet.productService.services;

import dev.abhijeet.productService.dtos.FakeStoreCategoryDto;
import dev.abhijeet.productService.dtos.FakeStoreProductDto;
import dev.abhijeet.productService.models.Category;
import dev.abhijeet.productService.models.Product;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements  ProductService{
    private RestTemplate restTemplate;
    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    @Override
    public Product getSingleProduct(Long id) {

//        FakeStoreProductDto fakeStoreProductDto =  restTemplate
//                .getForObject(
//                        "https://fakestoreapi.com/products/"+id,
//                         FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> responseEntity =  restTemplate.getForEntity(
                "https://fakestoreapi.com/products/"+id,
                FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageURL(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return  product;
    }
    @Override
    public Product createProduct(String title, String image, String description, String category, double price) {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setTitle(title);

        FakeStoreProductDto response = restTemplate
                .postForObject("https://fakestoreapi.com/products",
                        fakeStoreProductDto,
                        FakeStoreProductDto.class);

        return  response.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[]  response =
                restTemplate.getForObject("https://fakestoreapi.com/products"
                ,FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : response){
            products.add(fakeStoreProductDto.toProduct());
        }
        return products;
    }


    @Override
    public List<Category> getAllCategory() {
        FakeStoreCategoryDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                FakeStoreCategoryDto[].class
        );
        List<Category> categories = new ArrayList<>();
            for (FakeStoreCategoryDto dto : response) {
                categories.add(dto.toCategory());
            }

        return categories;
    }

    @Override
    public void deleteProduct(Long id) {
            restTemplate.delete(
                "https://fakestoreapi.com/products/{id}",id);
    }

    @Override
    public Product updateProduct(Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create a request entity with the updated product details
        HttpEntity<Product> requestEntity = new HttpEntity<>(product, headers);

        // Send a PUT request to the API endpoint
        ResponseEntity<Product> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/{id}",
                HttpMethod.PUT,
                requestEntity,
                Product.class,
                product.getId()
        );

        Product updatedProduct = responseEntity.getBody();
        return updatedProduct;
    }


}
