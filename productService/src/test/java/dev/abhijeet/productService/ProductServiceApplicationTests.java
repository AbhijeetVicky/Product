package dev.abhijeet.productService;

import dev.abhijeet.productService.models.Category;
import dev.abhijeet.productService.models.Product;
import dev.abhijeet.productService.repositotries.CategoryRepository;
import dev.abhijeet.productService.repositotries.ProductRepository;
import dev.abhijeet.productService.repositotries.projections.ProductWithTitleAndId;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

	@Autowired // -- Tells spring to inject the object of product repository
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Test
	void contextLoads() {
	}

	@Test
	public void testingQuery() {
		Product product = productRepository
				.getProductWithASpecificTitleAndId("iphone", 52L);

		System.out.println(product.getTitle());
	}

	@Test
	public void testingQuery2() {
		ProductWithTitleAndId product = productRepository
				.getProductWithASomeTitleAndId2("iphone", 52L);

		System.out.println(product.getId());
		System.out.println(product.getTitle());
	}
	@Test
	@Transactional
	public void testingFetchTypes(){
		Category category =  categoryRepository.findByTitle("Fan");
		//System.out.println(category.getTitle());
		System.out.println(category.getProducts()); // 2 query is fired i.e Lazy fetch
	}

	@Test
	public void testingFetchTypes2(){
		Category category =  categoryRepository.findByTitle("Fan");
		System.out.println(category.getTitle());

	}

	@Test
	public void testingFetchTypes3(){
		Optional<Category> category =  categoryRepository.findById(2L);
		System.out.println(category.get().getTitle());
	}
//		@Test
//		@Transactional
//	 public void nPlusProblem(){
//		 //Fetch type is Lazy
//		List<Category> categories = categoryRepository.findAll();
//		 // Get all cats and for each cat get the product and
//		 // print title of each product
//		 //Fetch type is Lazy
//			for(Category category: categories) {
//				for(Product product: category.getProducts()) {
//					System.out.println(product.getTitle());
//				}
//			}
//	 }


}
