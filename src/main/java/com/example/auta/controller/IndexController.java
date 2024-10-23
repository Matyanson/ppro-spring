package com.example.auta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/", "/test"})
    public String index() {
        return "index";
    }
}
