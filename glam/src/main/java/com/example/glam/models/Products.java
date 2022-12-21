package com.example.glam.models;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Products {
//    properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String details;
    private int price;
    private int stocks;
    private boolean hasStocks;

//    constructor

    public Products() {}

    public Products(
            String name,
            String details,
            int price,
            int stocks,
            boolean hasStocks) {
        this.name = name;
        this.details = details;
        this.price = price;
        this.stocks = stocks;
        this.hasStocks = hasStocks;
    }

    //    Setters and getters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStocks() {
        return stocks;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public boolean getHasStocks() {
        return hasStocks;
    }

    public void setHasStocks(boolean value) {
        this.hasStocks = value;
    }
}
