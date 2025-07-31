package com.ijse.o13auth_back_end.repository;

import com.ijse.o13auth_back_end.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
