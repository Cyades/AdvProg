package id.ac.ui.cs.advprog.eshop.model;

import java.util.UUID;

public class Car {
    private String carId;
    private String carName;
    private int carQuantity;
    private String carColor;

    // Getters and Setters
    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCarQuantity() {
        return carQuantity;
    }

    public void setCarQuantity(int carQuantity) {
        this.carQuantity = carQuantity;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public Car() {
        this.carId = UUID.randomUUID().toString();
    }
}