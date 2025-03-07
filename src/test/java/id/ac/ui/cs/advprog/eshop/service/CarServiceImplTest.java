package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;






@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {
    
    @Mock
    private CarRepository carRepository;
    
    @InjectMocks
    private CarServiceImpl carService;
    
    private Car car;
    
    @BeforeEach
    void setUp() {
        car = new Car();
        car.setCarId("car-123");
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(10);
    }
    
    @Test
    void testCreate() {
        when(carRepository.create(car)).thenReturn(car);
        
        Car result = carService.create(car);
        
        verify(carRepository).create(car);
        assertSame(car, result);
        assertEquals("Toyota", result.getCarName());
    }
    
    @Test
    void testFindAll() {
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        
        Car car2 = new Car();
        car2.setCarId("car-456");
        car2.setCarName("Honda");
        carList.add(car2);
        
        when(carRepository.findAll()).thenReturn(carList.iterator());
        
        List<Car> result = carService.findAll();
        
        verify(carRepository).findAll();
        assertEquals(2, result.size());
        assertEquals("Toyota", result.get(0).getCarName());
        assertEquals("Honda", result.get(1).getCarName());
    }
    
    @Test
    void testFindAllWithEmptyList() {
        when(carRepository.findAll()).thenReturn(Collections.emptyIterator());
        
        List<Car> result = carService.findAll();
        
        verify(carRepository).findAll();
        assertTrue(result.isEmpty());
    }
    
    @Test
    void testFindById() {
        when(carRepository.findById("car-123")).thenReturn(car);
        
        Car result = carService.findById("car-123");
        
        verify(carRepository).findById("car-123");
        assertNotNull(result);
        assertEquals("Toyota", result.getCarName());
    }
    
    @Test
    void testFindByIdNotFound() {
        when(carRepository.findById("nonexistent")).thenReturn(null);
        
        Car result = carService.findById("nonexistent");
        
        verify(carRepository).findById("nonexistent");
        assertNull(result);
    }
    
    @Test
    void testUpdate() {
        Car updatedCar = new Car();
        updatedCar.setCarId("car-123");
        updatedCar.setCarName("Updated Toyota");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity(15);
        
        carService.update("car-123", updatedCar);
        
        verify(carRepository).update("car-123", updatedCar);
    }
    
    @Test
    void testDeleteCarById() {
        carService.deleteCarById("car-123");
        
        verify(carRepository).delete("car-123");
    }
}
