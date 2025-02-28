package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.List;

public interface CarRetrievalService {
    List<Car> findAll();
    Car findById(String carId);
}