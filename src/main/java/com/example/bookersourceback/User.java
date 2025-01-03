package com.example.bookersourceback;

import jakarta.persistence.*;

@Entity
@Table(name="users", schema="mydatabase")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name="user_name")
    private String name;

    @Column(name="user_birthday")
    private String dateOfBirth;

    @Column(name="user_email")
    private String email;

    @Column(name="user_password")
    private String password;

    @Column(name="user_country")
    private String country;

    @Column(name="user_state")
    private String state;

    @Column(name="user_city")
    private String city;

    @Column(name="user_address")
    private String address;

    @Column(name="user_is_administrator")
    private boolean isAdministrator;

    public User(long id, String name,String dateOfBirth, String email, String password,
                String country, String state, String city, String address, boolean isAdministrator) {

        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
        this.isAdministrator = isAdministrator;
    }

    public long getId() {
        return id;
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

    public String getState() {
        return state;
    }

    public String getCity() {
        return this.city;
    }

    public String getAddress() {
        return this.address;
    }

    public boolean isAdministrator() {
        return this.isAdministrator;
    }
}
