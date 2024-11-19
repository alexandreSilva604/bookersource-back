package com.example.bookersourceback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/hotels")
public class HotelController {
    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewHotel(@RequestParam String name,
                                            @RequestParam String city,
                                            @RequestParam String state,
                                            @RequestParam String address,
                                            @RequestParam int zipCode) {

        Hotel newHotel = new Hotel(name, city, state, address, zipCode);
        hotelRepository.save(newHotel);

        return "Added to repository";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
