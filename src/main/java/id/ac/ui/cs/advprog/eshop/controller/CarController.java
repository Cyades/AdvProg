package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarCreationService;
import id.ac.ui.cs.advprog.eshop.service.CarRetrievalService;
import id.ac.ui.cs.advprog.eshop.service.CarUpdateService;
import id.ac.ui.cs.advprog.eshop.service.CarDeletionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarCreationService carCreationService;

    @Autowired
    private CarRetrievalService carRetrievalService;

    @Autowired
    private CarUpdateService carUpdateService;

    @Autowired
    private CarDeletionService carDeletionService;

    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "CreateCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car, Model model) {
        carCreationService.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String carListPage(Model model) {
        List<Car> allCars = carRetrievalService.findAll();
        model.addAttribute("cars", allCars);
        return "CarList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage(@PathVariable("carId") String carId, Model model) {
        Car car = carRetrievalService.findById(carId);
        model.addAttribute("car", car);
        return "EditCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        carUpdateService.update(car.getCarId(), car);
        return "redirect:listCar";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@PathVariable("carId") String carId) {
        carDeletionService.deleteCarById(carId);
        return "redirect:listCar";
    }
}