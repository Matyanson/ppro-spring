package com.example.auta.service;

import com.example.auta.model.Driver;
import com.example.auta.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    private DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(long id) {
        return driverRepository.findById(id).orElse(null);
    }

    public void deleteDriver(long id) {
        Optional<Driver> driver = driverRepository.findById(id);
        driver.ifPresent(driver1 -> driverRepository.delete(driver1));
    }

    public void saveDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public void updateDriver(Driver driver) {

    }
}
