package com.ijse.o14spring_boot_testing.repo;

import com.ijse.o14spring_boot_testing.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository extends JpaRepository<Product,Long> {
}
