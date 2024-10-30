package com.example.auta.service;

import com.example.auta.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface CarService {
    List<Car> getAllCars();
    public Car getCarById(long id);
    public void saveCar(Car car);

}
