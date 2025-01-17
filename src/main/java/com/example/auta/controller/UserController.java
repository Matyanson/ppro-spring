package com.example.auta.controller;

import com.example.auta.model.User;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import com.example.auta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Objects;

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
        if(Objects.equals(currentUser.getRole(), "ADMIN"))
            currentUser.setRole(user.getRole());

        userService.save(currentUser); // Save the updated user

        return "redirect:/user/detail/" + currentUser.getId(); // Redirect back to the profile
    }

    @GetMapping("/create")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // Empty user for the form
        return "user/create";
    }

    @PostMapping("/create")
    public String registerUser(
            @Valid @ModelAttribute User user,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        // Check for validation errors
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:user/create";
        }

        // Ensure username is unique
        if (userService.findByUsername(user.getUsername()) != null) {
            result.rejectValue("username", "error.user", "Username is already taken");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:user/create";
        }

        // Set role and encode password
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.save(user);

        redirectAttributes.addFlashAttribute("successMessage", "Registration successful! You can now log in.");
        return "redirect:/user/detail/" + user.getId();
    }
}
