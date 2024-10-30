package com.example.auta.service;

import com.example.auta.model.Car;
import com.example.auta.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(long id) {
        return carRepository.findById(id).orElse(null);
    }

    public void deleteCar(long id) {
        Optional<Car> car = carRepository.findById(id);
        car.ifPresent(car1 -> carRepository.delete(car1));
    }

    public void saveCar(Car car) {
        carRepository.save(car);
    }

    public void updateCar(Car car) {

    }
}
