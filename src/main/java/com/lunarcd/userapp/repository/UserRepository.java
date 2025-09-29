package com.lunarcd.userapp.repository;

import com.lunarcd.userapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContaining(String keyword);

    Page<User> findByAgeGreaterThan(int age, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.name LIKE %:keyword% OR u.email LIKE %:keyword%")
    List<User> searchByNameOrEmail(@Param("keyword") String keyword);
}

