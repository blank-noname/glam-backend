package com.example.glam.repositories;

import com.example.glam.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepositories extends JpaRepository<Users, Integer> {

    @Query(value = "SELECT * FROM users WHERE email = ?1 LIMIT 1", nativeQuery = true)
    Users findByEmail(String email);
    @Query(value = "SELECT * FROM users WHERE is_active = 1", nativeQuery = true)
    List<Users> findAllActive();


}
