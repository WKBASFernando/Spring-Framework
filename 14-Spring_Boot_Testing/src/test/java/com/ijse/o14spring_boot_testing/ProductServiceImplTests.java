package com.ijse.o14spring_boot_testing;

import com.ijse.o14spring_boot_testing.entity.Product;
import com.ijse.o14spring_boot_testing.repo.ProductRepository;
import com.ijse.o14spring_boot_testing.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
                .price(10.11)
                .build();
    }

    @Test
    void shouldSaveProduct(){
        //arrange
        when(productRepository.save(any(Product.class))).thenReturn(product);
        //action
        Product saveProduct = productService.createProduct(product);
        //assert
        Assertions.assertNotNull(saveProduct);
        Assertions.assertEquals(product, saveProduct);
        Assertions.assertEquals(1L, saveProduct.getId());
        verify(productRepository,times(1)).save(any(Product.class));
    }

    @Test
    void shouldUpdateProduct(){
        Product updateProduct = Product.builder()
                .id(1L)
                .name("Update Project")
                .price(10.11).build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(updateProduct);
        Product result = productService.updateProduct(updateProduct);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(updateProduct, result);
        Assertions.assertEquals("Update Project", result.getName());
        Assertions.assertEquals(10.11, result.getPrice());
        verify(productRepository,times(1)).findById(1L);
    }

    @Test
    void shouldGetProduct(){
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product product1 = productService.getProductById(1L);
        Assertions.assertNotNull(product1);
        Assertions.assertEquals(product, product1);
        Assertions.assertEquals(1L,product1.getId());
        verify(productRepository,times(1)).findById(1L);
    }
}
