package com.example.bookersourceback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
