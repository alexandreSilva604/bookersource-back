package com.example.bookersourceback;

import jakarta.persistence.*;

@Entity
@Table(name="rooms", schema="mydatabase")
public class Room {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="room_id")
    private long id;

    @Column(name="room_number")
    private int number;

    @Column(name="room_price_per_day")
    private double pricePerDay;

    @Column(name="room_capacity_people")
    private int peopleCapacity;

    @Column(name="room_quantity_beds")
    private int quantityBeds;

    @Column(name="room_floor")
    private int floor;

    @Column(name="room_hotel_id")
    private int hotelId;

    public Room() {}

    public Room(long id, int number, double pricePerDay, int peopleCapacity, int quantityBeds,
                int floor, int hotelId) {

        this.id = id;
        this.number = number;
        this.pricePerDay = pricePerDay;
        this.peopleCapacity = peopleCapacity;
        this.quantityBeds = quantityBeds;
        this.floor = floor;
        this.hotelId = hotelId;
    }

    public long getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public int getQuantityBeds() {
        return quantityBeds;
    }

    public int getFloor() {
        return floor;
    }

    public int getHotelId() {
        return hotelId;
    }
}
