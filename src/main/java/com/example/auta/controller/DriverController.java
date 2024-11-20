package com.example.auta.controller;

import com.example.auta.model.Car;
import com.example.auta.model.Driver;
import com.example.auta.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/drivers")
public class DriverController {
    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/")
    public String list(Model model) {

        model.addAttribute("Drivers", driverService.getAllDrivers());
        return "driver_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        Driver Driver = driverService.getDriverById(id);
        if(Driver == null) return "redirect:/drivers/";

        model.addAttribute("Driver", Driver);
        return "driver_detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        if(id < 0 || id >= driverService.getAllDrivers().size()) return "redirect:/drivers/";

        driverService.getAllDrivers().remove(id);
        return "delete";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("driver", new Driver());
        model.addAttribute("edit", false);
        return "driver_edit";
    }

    @PostMapping("/save")
    public String save(@Valid Driver driver, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "driver_edit";
        }
        driverService.saveDriver(driver);
        return "redirect:/drivers/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        if(id < 0 || id >= driverService.getAllDrivers().size()) return "redirect:/drivers/";

        Driver Driver = driverService.getAllDrivers().get((int)id);
        model.addAttribute("Driver", Driver);
        model.addAttribute("edit", true);
        return "driver_edit";
    }

}
