package com.example.auta.controller;

import com.example.auta.model.Car;
import com.example.auta.service.CarService;
import com.example.auta.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {
    CarService carService;
    DriverService driverService;

    public CarController(CarService carService, DriverService driverService) {
        this.carService = carService;
        this.driverService = driverService;
    }

    @GetMapping("/")
    public String list(Model model) {

        model.addAttribute("cars", carService.getAllCars());
        return "car_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        Car car = carService.getCarById(id);
        if(car == null) return "redirect:/cars/";

        model.addAttribute("car", car);
        return "car_detail";
    }

    @GetMapping("/delete/{id}")
    @RequestMapping("/cars")
    public String delete(@PathVariable long id) {
        if(id < 0 || id >= carService.getAllCars().size()) return "redirect:/cars/";

        carService.getAllCars().remove(id);
        return "delete";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("edit", false);
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "car_edit";
    }

    @PostMapping("/save")
    public String save(@Valid Car car, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("drivers", driverService.getAllDrivers());
            return "car_edit";
        }
        carService.saveCar(car);
        return "redirect:/cars/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        if(id < 0 || id >= carService.getAllCars().size()) return "redirect:/cars/";

        Car car = carService.getAllCars().get((int)id);
        model.addAttribute("car", car);
        model.addAttribute("edit", true);
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "car_edit";
    }

}
