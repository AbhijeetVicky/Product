package dev.abhijeet.productService.controllers;

import dev.abhijeet.productService.commons.AuthenticationCommons;
import dev.abhijeet.productService.dtos.CreateProductRequestDto;
import dev.abhijeet.productService.dtos.ErrorDto;
import dev.abhijeet.productService.dtos.UserDto;
import dev.abhijeet.productService.exceptions.InvalidTokenException;
import dev.abhijeet.productService.models.Category;
import dev.abhijeet.productService.models.Product;
import dev.abhijeet.productService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;
    private AuthenticationCommons authenticationCommons;

    public  ProductController(@Qualifier("selfProductService") ProductService productService, AuthenticationCommons authenticationCommons){
        this.productService=productService;
        this.authenticationCommons=authenticationCommons;
    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto  productRequestDto){
        return productService.createProduct(
        productRequestDto.getTitle(),
        productRequestDto.getDescription(),
        productRequestDto.getImage(),
         productRequestDto.getCategory(),
         productRequestDto.getPrice()
        );

    }

    @RequestMapping("/products" )
    public ResponseEntity< List<Product>>getAllProducts(@RequestHeader("Authorization") String token){
            UserDto userDto = authenticationCommons.validateToken(token);

            if(userDto == null){
                throw new InvalidTokenException("Un-Authorized");
            }

          List<Product> responseData = productService.getAllProducts();
         ResponseEntity< List<Product> > responseEntity =
                      new ResponseEntity<>(responseData, HttpStatusCode.valueOf(345));

         return responseEntity;
    }

    //Jackson
    @RequestMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){

        return productService.getSingleProduct(id);
    }
    @RequestMapping("/deleteProduct/{id}")
    public  void deleteProduct(@PathVariable("id") Long id){
          productService.deleteProduct(id);
    }
    @RequestMapping("/categories")
    public  List<Category> getAllCategories() {
        return productService.getAllCategory();

    }
    @PutMapping("/updateProduct")
    public  Product updateProductById(@RequestBody Product product){
         return  productService.updateProduct(product);
    }



}
