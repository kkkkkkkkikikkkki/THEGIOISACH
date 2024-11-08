package com.dan.datn.Repository;

import com.dan.datn.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTen(String ten);
    boolean existsByEmail(String email);
    User findByEmail(String email); // tìm admin bằng email
}