package com.example.bookersourceback;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Long.parseLong;

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

        try {
            User newUser = new User(0, userData.get("name"), userData.get("dateOfBirth"),
                    userData.get("email"), userData.get("password"), userData.get("country"),
                    userData.get("state"), userData.get("city"), userData.get("address"),
                    userData.get("phoneNumber"), userData.get("isAdministrator").equals("true"));

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

    @RequestMapping(path="/update",
            method=RequestMethod.PUT,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HashMap<String, User> updateUser(@RequestBody Map<String, String> userData) {

        HashMap<String, User> message = new HashMap<>();

        try {
            User userToUpdate = new User(parseLong(userData.get("id")), userData.get("name"), userData.get("dateOfBirth"),
                    userData.get("email"), userData.get("password"), userData.get("country"),
                    userData.get("state"), userData.get("city"), userData.get("address"),
                    userData.get("phoneNumber"), userData.get("isAdministrator").equals("true"));

            userRepository.save(userToUpdate);

            message.put("user", userRepository.findUserByEmailAndPassword(userData.get("email"), userData.get("password")));
            return message;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            message.put("user", null);
            return message;
        }
    }

    @RequestMapping(path="/authenticate",
            method=RequestMethod.POST,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, User> authenticateUser
            (@RequestBody Map<String, String> userInfo, HttpServletResponse response) {

        HashMap<String, User> message = new HashMap<>();

        try {
            User foundUser = userRepository.findUserByEmailAndPassword(userInfo.get("email"),
                    userInfo.get("password"));

            // NOT WORKING (?)
            if (foundUser != null) {
                Cookie sessionCookie = new Cookie("userStatus", "logged");

                sessionCookie.setHttpOnly(true);
                sessionCookie.setSecure(false);
                sessionCookie.setPath("/");
                sessionCookie.setDomain("localhost");

                response.addCookie(sessionCookie);

                message.put("user", foundUser);
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

            Cookie logoutCookie = new Cookie("userStatus", "loggedOut");

            logoutCookie.setMaxAge(0);
            logoutCookie.setHttpOnly(true);
            logoutCookie.setSecure(false);
            logoutCookie.setPath("/");
            logoutCookie.setDomain("localhost");

            response.addCookie(logoutCookie);

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
