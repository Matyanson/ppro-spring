package com.example.auta.controller;

import com.example.auta.model.Car;
import com.example.auta.service.CarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {
    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String list(Model model) {

        model.addAttribute("cars", carService.getAllCars());
        return "car_list";
    }

    @GetMapping("/detail/{index}")
    public String detail(Model model, @PathVariable int index) {
        Car car = carService.getCarById(index);
        if(car == null) return "redirect:/cars/";

        model.addAttribute("car", car);
        return "car_detail";
    }

    @GetMapping("/delete/{index}")
    @RequestMapping("/cars")
    public String delete(@PathVariable int index) {
        if(index < 0 || index >= carService.getAllCars().size()) return "redirect:/cars/";

        carService.getAllCars().remove(index);
        return "delete";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("edit", false);
        return "car_edit";
    }

    @PostMapping("/save")
    public String save(@Valid Car car, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "car_edit";
        }
        carService.saveCar(car);
        return "redirect:/cars/";
    }

    @GetMapping("/edit/{index}")
    public String edit(Model model, @PathVariable int index) {
        if(index < 0 || index >= carService.getAllCars().size()) return "redirect:/cars/";

        Car car = carService.getAllCars().get(index);
        car.setId(index);
        model.addAttribute("car", car);
        model.addAttribute("edit", true);
        return "car_edit";
    }

}
