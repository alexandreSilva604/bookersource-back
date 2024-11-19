package com.example.bookersourceback;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int zipCode;
    private String name, city, state, address;

    public Hotel(String name, String city, String state, String address, int zipCode) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.address = address;
        this.zipCode = zipCode;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getAddress() {
        return this.address;
    }

    public int getZipCode() {
        return this.zipCode;
    }
}
