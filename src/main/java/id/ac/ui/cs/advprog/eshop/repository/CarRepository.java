package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.Iterator;

public interface CarRepository {
    void create(Car car);
    Iterator<Car> findAll();
    Car findById(String carId);
    void update(String carId, Car car);
    void delete(String carId);
}