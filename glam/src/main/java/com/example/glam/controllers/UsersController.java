package com.example.glam.controllers;

import com.example.glam.Constants;
import com.example.glam.models.Users;
import com.example.glam.repositories.UsersRepositories;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class UsersController extends AppController{

    @Autowired
    private UsersRepositories user;


    @PostMapping("/api/users/register")
    public ResponseEntity<Object> register(@RequestBody Users newUser) {
        HashMap<String, String> response = new HashMap<>();
        String encodedPassword = new BCryptPasswordEncoder().encode(newUser.getPassword());

        user.save(new Users(
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getEmail(),
                newUser.getUsername(),
                encodedPassword,
                newUser.getAddress(),
                newUser.getContact(),
                newUser.getBday(),
                newUser.getIsAdmin(),
                newUser.getIsActive()
        ));
        response.put("result", "added");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/api/users/login")
    public ResponseEntity<Object> login(@RequestBody HashMap<String, Object> credentials) {
        HashMap<String, String> response = new HashMap<>();
        Users matchedUser = user.findByEmail(credentials.get("email").toString());

        if (matchedUser != null) {
            String enteredPasword = credentials.get("password").toString();
            boolean isPasswordMatched = new BCryptPasswordEncoder().matches(enteredPasword, matchedUser.getPassword());

            if (isPasswordMatched) {
                response.put("result", "successful");
                response.put("isAdmin", Boolean.toString(matchedUser.getIsAdmin()));
                response.put("email", matchedUser.getEmail());
                response.put("token", generateToken(matchedUser.getId(), matchedUser.getEmail()));
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("result", "incorrect_credentials");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            response.put("result", "user_not_found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/users")
    public List<Users> get() {
        return user.findAllActive();
    }


    private String generateToken(int id, String email) {
        long timestamp = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY);

        builder = builder.setIssuedAt(new Date(timestamp));
        builder = builder.setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY));

        builder = builder.claim("id", id);
        builder = builder.claim("email", email);

        return builder.compact();
    }


}
