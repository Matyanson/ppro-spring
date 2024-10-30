package com.example.auta.service;
import java.util.List;

import com.example.auta.model.Driver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface DriverService {
    List<Driver> getAllDrivers();
    public Driver getDriverById(long id);
    public void saveDriver(Driver Driver);

}
