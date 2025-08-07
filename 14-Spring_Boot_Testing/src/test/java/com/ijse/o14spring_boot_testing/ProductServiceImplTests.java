package com.ijse.o14spring_boot_testing;

import com.ijse.o14spring_boot_testing.entity.Product;
import com.ijse.o14spring_boot_testing.repo.ProductRepository;
import com.ijse.o14spring_boot_testing.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTests {
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;
    private Product product;
    @BeforeEach
    public void setup(){
        product = Product.builder()
                .id(1L)
                .name("Test Product")
                .price(100.0)
                .build();
    }

    @Test
    void shouldSaveProduct(){

    }
}
