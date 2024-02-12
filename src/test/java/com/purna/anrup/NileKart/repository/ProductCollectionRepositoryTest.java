package com.purna.anrup.NileKart.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.purna.anrup.NileKart.model.Product;

class ProductCollectionRepositoryTest {

	@Test
	public void testCreateProduct() {
	    Product product = new Product("Test Product","Test Description", 10.0,30);
	    ProductCollectionRepository service = new ProductCollectionRepository(); 

	    Product createdProduct = service.createProduct(product);

	    assertNotNull(createdProduct);
	    assertEquals(createdProduct.getProductId(), 1);
	    assertEquals(createdProduct.getName(), "Test Product");
	    assertEquals(createdProduct.getPrice(), 10.0, 0.001);
	}
	
	@Test
	public void testGetProductExisting() {
		Product product = new Product("Test Product","Test Description", 10.0,30);
	    ProductCollectionRepository service = new ProductCollectionRepository();
	    Product createdProduct=service.createProduct(product); 

	    Product retrievedProduct = service.getProduct(1); 

	    assertNotNull(retrievedProduct);
	    assertEquals(retrievedProduct.getProductId(), 1);
	    assertEquals(retrievedProduct.getName(), "Test Product");
	    assertEquals(retrievedProduct.getPrice(), 10.0, 0.001);
	}

	@Test
	public void testUpdateProductExisting() {
		Product product = new Product("Test Product","Test Description", 10.0,30);
	    ProductCollectionRepository service = new ProductCollectionRepository(); 

	    service.createProduct(product); 

	    String updateResult = service.updateProduct(1, new Product("Updated Product","Test Description", 20.0,30));
	    System.out.println(updateResult);
	    assertEquals(updateResult, "Product with ID 1 updated successfully");

	    Product retrievedProduct = service.getProduct(1);

	    assertEquals(retrievedProduct.getName(), "Updated Product");
	    assertEquals(retrievedProduct.getPrice(), 20.0, 0.001);
	}

	@Test
	public void testUpdateProductNonExistent() {
		Product product = new Product("Test Product","Test Description", 10.0,30);
	    ProductCollectionRepository service = new ProductCollectionRepository(); 
	    String updateResult = service.updateProduct(99, product);

	    assertEquals(updateResult, "Product with ID 99 not found update failed");
	}
	
	@Test
	public void testDeleteProductExisting() {
		Product product = new Product("Test Product","Test Description", 10.0,30);
	    ProductCollectionRepository service = new ProductCollectionRepository(); 

	    service.createProduct(product); 

	    String deleteResult = service.deleteProduct(1);

	    assertEquals(deleteResult, "Product with ID 1 deleted successfully");

	}

	@Test
	public void testDeleteProductNonExistent() {
		ProductCollectionRepository service = new ProductCollectionRepository(); 
	    String deleteResult = service.deleteProduct(99);

	    assertEquals(deleteResult, "Product with ID 99 not found delete failed");
	}

	@Test
	public void testApplyDiscountExisting() {
		Product product = new Product("Test Product","Test Description", 10.0,30);
	    ProductCollectionRepository service = new ProductCollectionRepository();

	    service.createProduct(product); 

	    String discountResult = service.applyDiscount(1, 10.0);

	    assertEquals(discountResult, "Discount of 10.0% applied successfully to product with ID 1");

	    Product retrievedProduct = service.getProduct(1);

	    assertEquals(retrievedProduct.getPrice(), 9.0, 0.001);
	}

	



}
