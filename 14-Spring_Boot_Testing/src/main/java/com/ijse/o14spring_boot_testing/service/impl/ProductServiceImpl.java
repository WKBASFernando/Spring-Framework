package com.ijse.o14spring_boot_testing.service.impl;

import com.ijse.o14spring_boot_testing.entity.Product;
import com.ijse.o14spring_boot_testing.repo.ProductRepository;
import com.ijse.o14spring_boot_testing.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return  productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product with id " + id + " not found")
        );

    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product existProduct = productRepository.getReferenceById(product.getId());
        existProduct.setName(product.getName());
        existProduct.setPrice(product.getPrice());
        return productRepository.save(existProduct);

    }

    @Override
    public void deleteProduct(Long id) {}
}
