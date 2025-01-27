package com.example.bookersourceback;

import jakarta.persistence.*;

@Entity
@Table(name="hotels", schema="mydatabase")
public class Hotel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="hotel_id")
    private long id;

    @Column(name="hotel_name")
    private String name;

    @Column(name="hotel_country")
    private String country;

    @Column(name="hotel_state")
    private String state;

    @Column(name="hotel_city")
    private String city;

    @Column(name="hotel_address")
    private String address;

    @Column(name="hotel_admin_id")
    private long adminId;

    public Hotel(String name, String country, String state, String city, String address, long adminId) {
        this.name = name;
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
        this.adminId = adminId;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCountry() {
        return this.country;
    }

    public String getState() {
        return this.state;
    }

    public String getCity() {
        return this.city;
    }

    public String getAddress() {
        return this.address;
    }

    public long getAdminId() {
        return adminId;
    }
}
