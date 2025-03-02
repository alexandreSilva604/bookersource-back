package com.example.bookersourceback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path="/hotels")
public class HotelController {
    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping(path="/add")
    public @ResponseBody Map<String, String> addNewHotel(@RequestBody Map<String, String> hotelData) {

        HashMap<String, String> message = new HashMap<>();

        try {
            Hotel newHotel = new Hotel(0, hotelData.get("name"),
                    hotelData.get("country"), hotelData.get("state"),
                    hotelData.get("city"), hotelData.get("address"),
                    Long.parseLong(hotelData.get("adminId")));

            hotelRepository.save(newHotel);

            message.put("message", "Hotel registered successfully!");
        }
        catch(Exception e) {
            message.put("message", e.getMessage());
        }

        return message;
    }

    @GetMapping(path="/userHotels/{adminId}")
    public @ResponseBody Map<String, ArrayList<Hotel>> findHotelsByUser(@PathVariable String adminId) {

        HashMap<String, ArrayList<Hotel>> foundHotels = new HashMap<>();

        try {
            foundHotels.put("hotels", hotelRepository.findHotelsByUserId(adminId));

            return foundHotels;
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

            return null;
        }
    }

    @GetMapping(path="/findHotel/{hotelId}")
    public @ResponseBody Map<String, Hotel> findHotel(@PathVariable String hotelId) {

        try {
            Hotel foundHotel = hotelRepository.findHotel(hotelId);

            Map<String, Hotel> hotelReturn = new HashMap<>();
            hotelReturn.put("hotel", foundHotel);

            return hotelReturn;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
