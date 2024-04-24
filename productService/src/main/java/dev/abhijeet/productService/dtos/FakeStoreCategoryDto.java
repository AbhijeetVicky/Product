package dev.abhijeet.productService.dtos;

import dev.abhijeet.productService.models.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreCategoryDto {

    private String title;

    public Category toCategory(){
        Category category = new Category();
        category.setTitle(FakeStoreCategoryDto.this.getTitle());

        return  category; // obj
    }

}
