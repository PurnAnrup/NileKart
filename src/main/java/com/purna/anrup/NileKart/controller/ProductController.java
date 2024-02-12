package com.purna.anrup.NileKart.controller;
import org.springframework.web.bind.annotation.*;

import com.purna.anrup.NileKart.model.Product;
import com.purna.anrup.NileKart.repository.ProductCollectionRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductCollectionRepository repository;
    
    
    
    public ProductController(ProductCollectionRepository repository) {
		this.repository = repository;
	}

	@PostMapping
    public Product createProduct(@RequestBody Product product) {
        return repository.createProduct(product);
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable int productId) {
        Product product = repository.getProduct(productId);
        return product;
    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable int productId, @RequestBody Product product) {
        return repository.updateProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable int productId) {
        return repository.deleteProduct(productId);
    }
    
    @PutMapping("/apply-discount/{productId}")
    public String applyDiscount(@PathVariable int productId, @RequestParam double discountPercentage) {
        return repository.applyDiscount(productId, discountPercentage);
    }

    @PutMapping("/apply-tax/{productId}")
    public String applyTax(@PathVariable int productId, @RequestParam double taxRate) {
        return repository.applyTax(productId, taxRate);
    }
}