package com.example.auta.service;

import com.example.auta.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface CarService {
    ArrayList<Car> getAllCars();
    public Car getCarById(int id);
    public void saveCar(Car car);

}
