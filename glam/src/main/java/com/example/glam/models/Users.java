package com.example.glam.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class Users {
//properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private String address;

    private String contact;

    private String bday;

    private boolean isAdmin;

    private boolean isActive;


//    contructors
    public Users() { }

    public Users(String firstName, String lastName, String email, String username, String password, String address, String contact, String bday, boolean isAdmin, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
        this.contact = contact;
        this.bday = bday;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
    }

    //    setters and getters


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean value) {
        this.isActive = value;
    }


    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(boolean value) {
        this.isAdmin = value;
    }


}
