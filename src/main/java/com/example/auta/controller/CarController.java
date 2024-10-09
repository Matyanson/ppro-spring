package com.example.auta.controller;

import com.example.auta.model.Car;
import com.example.auta.service.CarService;
import com.example.auta.service.CarServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String list(Model model) {

        model.addAttribute("cars", carService.getAllCars());
        return "list";
    }

    @GetMapping("/detail/{index}")
    public String detail(Model model, @PathVariable int index) {
        Car car = carService.getCarById(index);
        if(car == null) return "redirect:/";

        model.addAttribute("car", car);
        return "detail";
    }

    @GetMapping("/delete/{index}")
    public String delete(@PathVariable int index) {
        if(index < 0 || index >= carService.getAllCars().size()) return "redirect:/";

        carService.getAllCars().remove(index);
        return "delete";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("edit", false);
        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Car car) {
        carService.saveCar(car);
        return "redirect:/";
    }

    @GetMapping("/edit/{index}")
    public String edit(Model model, @PathVariable int index) {
        if(index < 0 || index >= carService.getAllCars().size()) return "redirect:/";

        Car car = carService.getAllCars().get(index);
        car.setId(index);
        model.addAttribute("car", car);
        model.addAttribute("edit", true);
        return "edit";
    }

}
