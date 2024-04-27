package dev.abhijeet.productService.controllers;

import dev.abhijeet.productService.dtos.CreateProductRequestDto;
import dev.abhijeet.productService.dtos.ErrorDto;
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
    ProductService productService;
    public   ProductController(@Qualifier("selfProductService") ProductService productService){
        this.productService=productService;
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
//    @RequestMapping("/products")
//    public List<Product> getAllProducts(){
//        return productService.getAllProducts();
//    }

    // ResponseEntity contains everything that a response requires:
    // Data, Status code and headers
    @RequestMapping("/products")
    public ResponseEntity< List<Product> >getAllProducts(){
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






//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<ErrorDto> handleNullPointerException(){
//        ErrorDto errorDto = new  ErrorDto();
//        errorDto.setMessage("Something went wrong. Please try again");
//
////        ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>( errorDto,
////                HttpStatusCode.valueOf(404));
//       return  new  ResponseEntity<>(errorDto,
//               HttpStatusCode.valueOf(404));
//    }

}
