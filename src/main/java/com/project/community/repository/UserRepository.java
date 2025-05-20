package com.project.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.project.community.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

