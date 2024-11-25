package com.example.bookersourceback;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int zipCode;
    private String name, email, password, country, state, city, address;
    private boolean isAdministrator;

    public User(String name, String email, String password,
                String country, String state, String city, String address,
                int zipCode, boolean isAdministrator) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.country = country;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
        this.isAdministrator = isAdministrator;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }
}
