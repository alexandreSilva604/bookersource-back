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
    private String name, dateOfBirth, email, password, country, state, city, address;
    private boolean isAdministrator;

    public User(String name,String dateOfBirth, String email, String password,
                String country, String state, String city, String address,
                int zipCode, boolean isAdministrator) {

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.country = country;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
        this.isAdministrator = isAdministrator;
    }

    public String getName() {
        return this.name;
    }

    public String getdateOfBirth() {
        return this.dateOfBirth;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCity() {
        return this.city;
    }

    public String getAddress() {
        return this.address;
    }

    public int getZipCode() {
        return this.zipCode;
    }

    public boolean isAdministrator() {
        return this.isAdministrator;
    }
}
