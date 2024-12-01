package com.example.bookersourceback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser(@RequestParam String name,
                                            @RequestParam String dateOfBirth,
                                            @RequestParam String email,
                                            @RequestParam String password,
                                            @RequestParam String country,
                                            @RequestParam String state,
                                            @RequestParam String city,
                                            @RequestParam String address,
                                            @RequestParam int zipCode,
                                            @RequestParam boolean isAdministrator) {

        User newUser = new User(name, dateOfBirth, email, password, country, state,
                city, address, zipCode, isAdministrator);
        userRepository.save(newUser);

        return "Added to repository";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
