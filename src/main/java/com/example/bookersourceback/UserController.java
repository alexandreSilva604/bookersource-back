package com.example.bookersourceback;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
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
            (@RequestBody Map<String, String> userInfo, HttpServletResponse response) {

        HashMap<String, User> message = new HashMap<>();
        System.out.println("Received data:\n" + userInfo);

        try {
            User foundUser = userRepository.findUserByEmailAndPassword(userInfo.get("email"),
                    userInfo.get("password"));

            message.put("user", foundUser);

            // NOT WORKING (?)
            if (foundUser != null) {
                ResponseCookie sessionCookie =
                        ResponseCookie.from("userStatus", "logged")
                                .httpOnly(true)
                                .secure(true)
                                .path("/")
                                .build();

                response.addHeader("Set-Cookie", sessionCookie.toString());
            }

            return message;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping(path = "/logout")
    public @ResponseBody HashMap<String, String> userLogout(HttpServletResponse response) {

        HashMap<String, String> statusMessage = new HashMap<>();

        try {

            ResponseCookie logoutCookie =
                    ResponseCookie.from("userStatus", "loggedOut")
                            .httpOnly(true)
                            .secure(true)
                            .maxAge(0)
                            .path("/")
                            .build();

            response.addHeader("Set-Cookie", logoutCookie.toString());

            statusMessage.put("message", "User has logged out.");

            return statusMessage;
        }
        catch(Exception e) {
            statusMessage.put("message", e.getMessage());
            return statusMessage;
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
