package com.example.auta.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "drivers")
public class Driver {
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;

    @Min(value = 18)
    @Max(value = 100)
    private int age;

    @Min(value = 20000)
    private int salary;

    @OneToMany(mappedBy = "driver")
    private List<Car> cars;


    public void setId(long index) {
        this.id = index;
    }

    public long getId() {
        return id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
