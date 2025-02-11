package com.example.bookersourceback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path="/rooms")
public class roomController {
    @Autowired
    private RoomRepository roomRepository;

    @PostMapping(path="/add")
    public @ResponseBody Map<String, String> addRoom(Map<String, String> roomData) {

        HashMap <String, String> message = new HashMap<>();

        try {
            Room newRoom = new Room(0, Integer.parseInt(roomData.get("number")),
                    Double.parseDouble(roomData.get("pricePerDay")),
                    Integer.parseInt(roomData.get("peopleCapacity")),
                    Integer.parseInt(roomData.get("quantityBeds")),
                    Integer.parseInt(roomData.get("floor")),
                    Long.parseLong(roomData.get("hotelId")), roomData.get("imagePath"));

            roomRepository.save(newRoom);

            message.put("message", "Room added successfully!");
        }
        catch(Exception e) {
            message.put("message", e.getMessage());
        }

        return message;
    }
}
