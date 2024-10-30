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
    DriverService DriverService;

    public DriverController(DriverService DriverService, @Qualifier("driverService") DriverService driverService) {
        this.DriverService = DriverService;
        this.driverService = driverService;
    }

    @GetMapping("/")
    public String list(Model model) {

        model.addAttribute("Drivers", DriverService.getAllDrivers());
        return "driver_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        Driver Driver = DriverService.getDriverById(id);
        if(Driver == null) return "redirect:/drivers/";

        model.addAttribute("Driver", Driver);
        return "driver_detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        if(id < 0 || id >= DriverService.getAllDrivers().size()) return "redirect:/drivers/";

        DriverService.getAllDrivers().remove(id);
        return "delete";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("Driver", new Driver());
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
        if(id < 0 || id >= DriverService.getAllDrivers().size()) return "redirect:/drivers/";

        Driver Driver = DriverService.getAllDrivers().get((int)id);
        model.addAttribute("Driver", Driver);
        model.addAttribute("edit", true);
        return "driver_edit";
    }

}
