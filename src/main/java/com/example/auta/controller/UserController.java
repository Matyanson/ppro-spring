package com.example.auta.controller;

import com.example.auta.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import com.example.auta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // Display user details page (accessible to all users)
    @GetMapping("/detail/{id}")
    public String showUserDetails(Model model, @PathVariable Long id, Principal principal) {
        User user = userService.findById(id);

        if (user == null) {
            return "redirect:/error"; // or handle the case when the user is not found
        }

        model.addAttribute("user", user);

        // Check if the logged-in user is the same as the user being viewed
        if (principal != null && principal.getName().equals(user.getUsername())) {
            model.addAttribute("canEdit", true); // Allows the user to edit their own profile
        }

        return "user/detail";
    }

    // Show user edit form (only for the logged-in user)
    @GetMapping("/edit")
    public String editUserProfile(Model model, Principal principal) {
        String username = principal.getName();
        User currentUser = userService.findByUsername(username);

        if (currentUser == null) {
            return "redirect:/error"; // or handle the case when the user is not found
        }

        model.addAttribute("user", currentUser);
        return "user/edit";
    }

    // Save user profile after editing
    @PostMapping("/edit")
    public String updateUserProfile(@ModelAttribute User user, Principal principal) {
        String username = principal.getName();
        User currentUser = userService.findByUsername(username);

        if (currentUser == null) {
            return "redirect:/error"; // or handle the case when the user is not found
        }

        // Update user details (e.g., username cannot be changed, just other fields)
        currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        // Update role only if admin is logged in
        if(currentUser.getRole() == "ADMIN")
            currentUser.setRole(user.getRole());

        userService.save(currentUser); // Save the updated user

        return "redirect:/user/detail/" + currentUser.getId(); // Redirect back to the profile
    }
}
