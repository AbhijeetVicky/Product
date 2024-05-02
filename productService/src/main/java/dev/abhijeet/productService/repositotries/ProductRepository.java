package dev.abhijeet.productService.repositotries;

import dev.abhijeet.productService.models.Category;
import dev.abhijeet.productService.models.Product;
import dev.abhijeet.productService.repositotries.projections.ProductWithTitleAndId;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long>{
    @Override
      Product  save(Product entity);
       Product findByIdIs(Long id);
       List<Product> findAll();
        void  deleteById(Long id);


    // HQL
    @Query(value = "select p from Product p where p.title= :title and p.id = :id")
    Product getProductWithASpecificTitleAndId(@Param("title") String title, @Param("id") Long id);


    //Sql - NAtive Query
//    @Query(value="select *  from  product where id=:id and title =: title")
//    Product getProductWithASomrTitleAndId(@Param("title") String title,
//                                              @Param("id") Long id);


    @Query("select p.id, p.title from Product p where p.title = :title and p.id = :id")
    ProductWithTitleAndId getProductWithASomeTitleAndId2(@Param("title") String title, @Param("id") Long id);

}
