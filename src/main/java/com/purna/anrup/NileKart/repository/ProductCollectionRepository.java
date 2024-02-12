package com.purna.anrup.NileKart.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.purna.anrup.NileKart.model.Product;

import jakarta.annotation.PostConstruct;

@Repository
public class ProductCollectionRepository {
	
	private final Map<Integer, Product> products = new HashMap<>();
    private int productIdCounter = 1;
    
    @PostConstruct
    private void init() {
    	Product product = new Product("Akuma","a Devil from Japan",67.99,4);
    	product.setProductId(productIdCounter++);
    	products.put(product.getProductId(), product);
    }

    public Product createProduct(Product product) {
        product.setProductId(productIdCounter++);
        products.put(product.getProductId(), product);
        return product;
    }

    public Product getProduct(int productId) {
        Product product = products.get(productId);
        if (product == null) {
            throw new RuntimeException("Product with ID " + productId + " not found");
        }
        return product;
    }

    public String updateProduct( int productId, Product product) {
        if (!products.containsKey(productId)) {
            return "Product with ID " + productId + " not found update failed" ;
        }
        product.setProductId(productId);
        products.put(productId, product);
        return "Product with ID " + productId + " updated successfully";
    }

    public String deleteProduct(int productId) {
        if (!products.containsKey(productId)) {
            return "Product with ID " + productId + " not found delete failed";
        }
        products.remove(productId);
        return "Product with ID " + productId + " deleted successfully";
    }
    
    public String applyDiscount(int productId,double discountPercentage) {
        Product product = products.get(productId);
        if (product == null) {
            throw new RuntimeException("Product with ID " + productId + " not found");
        }

        double discountedPrice = product.getPrice() * (1 - discountPercentage / 100);
        product.setPrice(discountedPrice);

        return "Discount of " + discountPercentage + "% applied successfully to product with ID " + productId;
    }

    public String applyTax(int productId,double taxRate) {
        Product product = products.get(productId);
        if (product == null) {
            throw new RuntimeException("Product with ID " + productId + " not found");
        }

        double taxedPrice = product.getPrice() * (1 + taxRate / 100);
        product.setPrice(taxedPrice);

        return "Tax of " + taxRate + "% applied successfully to product with ID " + productId;
    }

}
