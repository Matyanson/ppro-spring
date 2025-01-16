package com.example.auta.controller;


import com.example.auta.model.User;
import com.example.auta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    // Redirect the logged-in user to their detail page
    @GetMapping
    public String redirectToUserDetail(Principal principal) {
        if (principal != null) {
            String username = principal.getName(); // Get the logged-in user's username
            User user = userService.findByUsername(username);

            if (user != null) {
                return "redirect:/user/detail/" + user.getId(); // Redirect to the user's detail page
            }
        }

        // If there's no user (logged out or not found), redirect to a generic error page or login
        return "redirect:/login";
    }
}
