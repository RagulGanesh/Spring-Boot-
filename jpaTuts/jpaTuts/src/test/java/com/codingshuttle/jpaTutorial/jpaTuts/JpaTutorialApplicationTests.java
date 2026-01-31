package com.codingshuttle.jpaTutorial.jpaTuts;

import com.codingshuttle.jpaTutorial.jpaTuts.entities.ProductEntity;
import com.codingshuttle.jpaTutorial.jpaTuts.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.DependsOn;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaTutorialApplicationTests {

	@Autowired
	ProductRepository productRepository;
	@Test
	void contextLoads() {
	}

	@Test
    void testRepository(){
		ProductEntity productEntity = ProductEntity.builder()
				.sku("nesle2345")
				.title("Nestle Chocolate")
				.price(BigDecimal.valueOf(456.89))
				.quantity(56)
				.build();
		ProductEntity savedProductEntity = productRepository.save(productEntity);
		System.out.println(savedProductEntity);
	}

	@Test
	void getRepository(){
		List<ProductEntity> entities = productRepository.findAll();
		System.out.println(entities);
	}

//	@Test
//	void getProductByName(){
//		List<ProductEntity> entities = productRepository.findByTitle("parle");
//		System.out.println(entities);
//	}

	@Test
	void getProductCreatedAfter() throws InterruptedException {
		ProductEntity productEntity = ProductEntity.builder()
				.sku("bourbon")
				.title("Bourbon Biscuits")
				.price(BigDecimal.valueOf(5698))
				.quantity(56)
				.build();
		ProductEntity savedProductEntity = productRepository.save(productEntity);
		List<ProductEntity> entities = productRepository
				.findByCreatedAtAfter(LocalDateTime.of(2026,1,1,0,0,0));
		System.out.println(entities);
	}

	@Test
	void getByQuantityAndPrice(){
		List<ProductEntity> entities = productRepository.findByQuantityAndPrice(10,BigDecimal.valueOf(89.87));
		System.out.println(entities);
	}

	@Test
	void getSingleFromRepo(){
		Optional<ProductEntity> productEntity = productRepository.findByTitleAndPrice("cadbury",BigDecimal.valueOf(89.87));
		productEntity.ifPresent(System.out::println);
	}

}
