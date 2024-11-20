package com.example.auta.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cars")
public class Car {
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 7, max = 7, message = "Must be size of 7")
    private String spz;
    @NotBlank
    private String color;
    private String model;

    @Min(value = 30)
    @Max(value = 100)
    private float tankVolume;

    @Min(value = 2)
    @Max(value = 100)
    private int numberOfSeats;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public Car(String spz, String color, String model, float tankVolume, int numberOfSeats) {
        this.id = -1;
        this.spz = spz;
        this.color = color;
        this.model = model;
        this.tankVolume = tankVolume;
        this.numberOfSeats = numberOfSeats;
    }

    public Car() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpz() {
        return spz;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getTankVolume() {
        return tankVolume;
    }

    public void setTankVolume(float tankVolume) {
        this.tankVolume = tankVolume;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
