package com.example.springassessment.service;


import com.example.springassessment.entity.Product;
import com.example.springassessment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }
    public Optional<Product> getProductByName(String productName) {
        return productRepository.findByName(productName);
    }
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public Product updateProduct(Long productId, Product updatedProduct) {
        if (productRepository.existsById(productId)) {
            updatedProduct.setId(productId);
            return productRepository.save(updatedProduct);
        } else {
            throw new NoSuchElementException("Product not found with id: " + productId);
        }
    }
    public void deleteProduct(Long productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            throw new NoSuchElementException("Product not found with id: " + productId);
        }
    }
}














