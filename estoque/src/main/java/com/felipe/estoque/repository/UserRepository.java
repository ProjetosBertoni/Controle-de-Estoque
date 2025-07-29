package com.felipe.estoque.repository;

import com.felipe.estoque.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByEmail(String username);
}
