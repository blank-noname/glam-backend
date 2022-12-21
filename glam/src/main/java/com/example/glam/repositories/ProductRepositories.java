package com.example.glam.repositories;

import com.example.glam.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositories extends JpaRepository<Products, Integer> {
    @Query(value = "SELECT * FROM products WHERE is_active = 1", nativeQuery = true)
    List<Products> findAllActive();
}
