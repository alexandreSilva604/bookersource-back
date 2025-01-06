package com.example.bookersourceback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path="/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path="/add",
            method=RequestMethod.POST,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, String> addNewUser(@RequestBody Map<String, String> userData) {

        HashMap<String, String> message = new HashMap<>();
        System.out.println("Received data:\n" + userData);

        try {
            User newUser = new User(0, userData.get("name"), userData.get("dateOfBirth"),
                    userData.get("email"), userData.get("password"), userData.get("country"),
                    userData.get("state"), userData.get("city"), userData.get("address"),
                    userData.get("isAdministrator").equals("true"));
            userRepository.save(newUser);

            message.put("status", "200");
            message.put("message", "User registered successfully.");

            return message;
        }
        catch (Exception e) {
            message.put("status", "500");
            message.put("message", e.getMessage());
            return message;
        }
    }

    @RequestMapping(path="/authenticate",
            method=RequestMethod.POST,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, User> authenticateUser
            (@RequestBody Map<String, String> userInfo) {

        HashMap<String, User> message = new HashMap<>();
        System.out.println("Received data:\n" + userInfo);

        try {
            User foundUser = userRepository.findUserByEmailAndPassword(userInfo.get("email"),
                    userInfo.get("password"));

            message.put("user", foundUser);

            return message;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
