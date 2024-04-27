package dev.abhijeet.productService.repositotries;

import dev.abhijeet.productService.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByTitle(String title);

    Category save(Category category);


}
