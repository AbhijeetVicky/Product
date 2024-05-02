package dev.abhijeet.productService.repositotries.projections;

import lombok.Getter;
import lombok.Setter;

// Somwthing like DTOs except to get data from db and our app

public interface ProductWithTitleAndId {
    // put getter method for corresponding attributes
    Long getId();
    String getTitle();
}
