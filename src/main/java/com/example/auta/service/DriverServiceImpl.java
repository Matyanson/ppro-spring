package com.example.auta.service;

import com.example.auta.model.Driver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DriverServiceImpl implements DriverService {
    ArrayList<Driver> drivers = new ArrayList<>();

    @Override
    public ArrayList<Driver> getAllDrivers() {
        return drivers;
    }

    public Driver getDriverById(int id) {
        if(id < 0 || id >= drivers.size()) return null;

        return drivers.get(id);
    }

    public void saveDriver(Driver driver) {
        int id = driver.getId();
        if(id > -1 && id < drivers.size()) {
            drivers.set(id, driver);
        } else {
            drivers.add(driver);
        }
    }

    public void updateDriver(Driver driver) {

    }
}
