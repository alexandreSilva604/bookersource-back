package com.example.bookersourceback;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface HotelRepository extends CrudRepository<Hotel, Integer> {

    @Query(value="SELECT h FROM Hotel h WHERE h.adminId=:adminId")
    ArrayList<Hotel> findHotelsByUserId(String adminId);
}
