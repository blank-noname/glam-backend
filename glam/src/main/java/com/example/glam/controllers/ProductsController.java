package com.example.glam.controllers;

import com.example.glam.models.Products;
import com.example.glam.models.Users;
import com.example.glam.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;

@RestController
public class ProductsController extends AppController{
    @Autowired
    private ProductRepositories productRepo;

    @GetMapping("/api/products")
    public List<Products> get() {
        return productRepo.findAllActive();
    }

    @PostMapping("/api/products")
    public ResponseEntity<Object> add (@RequestBody Products prod){
        HashMap<String, String> response = new HashMap<>();
         productRepo.save(new Products(
                 prod.getName(),
                 prod.getDetails(),
                 prod.getPrice(),
                 prod.getStocks(),
                 prod.getHasStocks()
         ));
        response.put("result", "added");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
