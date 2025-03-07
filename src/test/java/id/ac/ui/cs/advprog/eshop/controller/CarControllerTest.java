package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;



class CarControllerTest {
    
    @Mock
    private CarService carService;
    
    @Mock
    private Model model;
    
    @InjectMocks
    private CarController carController;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testCreateCarPage() {
        String viewName = carController.createCarPage(model);
        
        verify(model).addAttribute(eq("car"), any(Car.class));
        assertEquals("CreateCar", viewName);
    }
    
    @Test
    void testCreateCarPost() {
        Car car = new Car();
        car.setCarName("Toyota");
        
        String viewName = carController.createCarPost(car, model);
        
        verify(carService).create(car);
        assertEquals("redirect:listCar", viewName);
    }
    
    @Test
    void testCarListPage() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        when(carService.findAll()).thenReturn(cars);
        
        String viewName = carController.carListPage(model);
        
        verify(model).addAttribute("cars", cars);
        assertEquals("CarList", viewName);
    }
    
    @Test
    void testEditCar() {
        String carId = "car-123";
        Car car = new Car();
        when(carService.findById(carId)).thenReturn(car);
        
        String viewName = carController.editCar(carId, model);
        
        verify(carService).findById(carId);
        verify(model).addAttribute("car", car);
        assertEquals("EditCar", viewName);
    }
    
    @Test
    void testEditCarPost() {
        Car car = new Car();
        car.setCarId("car-123");
        car.setCarName("Updated Toyota");
        
        String viewName = carController.editCarPost(car, model);
        
        verify(carService).update(car.getCarId(), car);
        assertEquals("redirect:listCar", viewName);
    }
    
    @Test
    void testDeleteCar() {
        String carId = "car-123";
        
        String viewName = carController.deleteCar(carId);
        
        verify(carService).deleteCarById(carId);
        assertEquals("redirect:listCar", viewName);
    }
}
