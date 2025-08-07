package com.ijse.o14spring_boot_testing.service;

import com.ijse.o14spring_boot_testing.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getProductById(Long id);
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public void deleteProduct(Long id);
}
