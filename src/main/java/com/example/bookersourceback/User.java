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
    private String name, email, password;
    private boolean isAdministrator;

    public User(String name, String email, String password, int zipCode, boolean isAdministrator) {

        this.name = name;
        this.email = email;
        this.password = password;
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

    public int getZipCode() {
        return zipCode;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }
}
