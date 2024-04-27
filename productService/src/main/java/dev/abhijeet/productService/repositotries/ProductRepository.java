package dev.abhijeet.productService.repositotries;


import dev.abhijeet.productService.models.Category;
import dev.abhijeet.productService.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long>{
    @Override
      Product  save(Product entity);
       Product findByIdIs(Long id);
       List<Product> findAll();
        void  deleteById(Long id);



}
