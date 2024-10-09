package com.example.auta.service;

import com.example.auta.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    ArrayList<Car> cars = new ArrayList<>();

    @Override
    public ArrayList<Car> getAllCars() {
        return cars;
    }

    public Car getCarById(int id) {
        if(id < 0 || id >= cars.size()) return null;

        Car car = cars.get(id);
        return car;
    }

    public void saveCar(Car car) {
        int id = car.getId();
        if(id > -1 && id < cars.size()) {
            cars.set(id, car);
        } else {
            cars.add(car);
        }
    }

    public void updateCar(Car car) {

    }
}
