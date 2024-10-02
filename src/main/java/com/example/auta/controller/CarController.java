package com.example.auta.controller;

import com.example.auta.model.Car;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public class CarController {
    List<Car> cars = new ArrayList<>();

    @GetMapping("/")
    public String list(Model model) {
        cars.add(new Car(
                "ABC",
                "blue",
                "Z",
                20.5f,
                5
        ));

        model.addAttribute("cars", cars);
        return "list";
    }

    @GetMapping("/detail/index")
    public String detail(Model model, @PathVariable int index) {
        if(index < 0 || index >= cars.size()) return "redirect:/";

        Car car = cars.get(index);

        model.addAttribute("car", car);
        return "detail";
    }

}
