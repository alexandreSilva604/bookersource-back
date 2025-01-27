package com.example.bookersourceback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping(path="/hotels")
public class HotelController {
    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping(path="/add")
    public @ResponseBody HashMap<String, String> addNewHotel(@RequestParam String name,
                                            @RequestParam String country,
                                            @RequestParam String state,
                                            @RequestParam String city,
                                            @RequestParam String address,
                                            @RequestParam long adminId) {

        HashMap<String, String> message = new HashMap<>();

        try {
            Hotel newHotel = new Hotel(name, country, state, city, address, adminId);
            hotelRepository.save(newHotel);

            message.put("message", "Hotel registered successfully!");
            return message;
        }
        catch(Exception e) {

            message.put("message", e.getMessage());
            return message;
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
