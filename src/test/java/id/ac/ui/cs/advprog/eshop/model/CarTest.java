package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

class CarTest {
    private Car car;
    
    @BeforeEach
    void setUp() {
        car = new Car();
        car.setCarId("ea122b96-f98e-4f54-9a3d-28fdbb6943bf");
        car.setCarName("Toyota Avanza");
        car.setCarColor("Silver");
        car.setCarQuantity(5);
    }

    @Test
    void testCarConstructor() {
        Car newCar = new Car();
        assertNotNull(newCar.getCarId());
        try {
            UUID uuid = UUID.fromString(newCar.getCarId());
            assertEquals(uuid.toString(), newCar.getCarId());
        } catch (IllegalArgumentException e) {
            fail("carId is not a valid UUID string");
        }
    }
    
    @Test
    void testMultipleCarConstructor() {
        Car car1 = new Car();
        Car car2 = new Car();
        
        assertNotNull(car1.getCarId());
        assertNotNull(car2.getCarId());
        assertNotEquals(car1.getCarId(), car2.getCarId());
    }
}