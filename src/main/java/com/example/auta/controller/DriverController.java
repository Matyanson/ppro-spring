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

    @GetMapping("/detail/{index}")
    public String detail(Model model, @PathVariable int index) {
        Driver Driver = DriverService.getDriverById(index);
        if(Driver == null) return "redirect:/drivers/";

        model.addAttribute("Driver", Driver);
        return "driver_detail";
    }

    @GetMapping("/delete/{index}")
    public String delete(@PathVariable int index) {
        if(index < 0 || index >= DriverService.getAllDrivers().size()) return "redirect:/drivers/";

        DriverService.getAllDrivers().remove(index);
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

    @GetMapping("/edit/{index}")
    public String edit(Model model, @PathVariable int index) {
        if(index < 0 || index >= DriverService.getAllDrivers().size()) return "redirect:/drivers/";

        Driver Driver = DriverService.getAllDrivers().get(index);
        Driver.setId(index);
        model.addAttribute("Driver", Driver);
        model.addAttribute("edit", true);
        return "driver_edit";
    }

}
