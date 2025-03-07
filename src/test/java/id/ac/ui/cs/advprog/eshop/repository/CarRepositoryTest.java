package id.ac.ui.cs.advprog.eshop.repository;


import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;


class CarRepositoryTest {

    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
    }

    @Test
    void testCreateCarWithoutId() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        
        Car result = carRepository.create(car);
        
        assertNotNull(result.getCarId());
        assertEquals("Toyota", result.getCarName());
        assertEquals("Red", result.getCarColor());
        assertEquals(10, result.getCarQuantity());
    }

    @Test
    void testCreateCarWithId() {
        Car car = new Car();
        car.setCarId("existing-id");
        car.setCarName("Honda");
        car.setCarColor("Blue");
        car.setCarQuantity(5);
        
        Car result = carRepository.create(car);
        
        assertEquals("existing-id", result.getCarId());
    }

    @Test
    void testFindAllEmpty() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindAllWithCars() {
        // Add some cars
        Car car1 = new Car();
        car1.setCarName("Toyota");
        car1.setCarColor("Red");
        car1.setCarQuantity(10);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarName("Honda");
        car2.setCarColor("Blue");
        car2.setCarQuantity(5);
        carRepository.create(car2);

        // Test findAll
        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        assertEquals("Toyota", carIterator.next().getCarName());
        assertTrue(carIterator.hasNext());
        assertEquals("Honda", carIterator.next().getCarName());
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindByIdFound() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        Car created = carRepository.create(car);
        
        Car found = carRepository.findById(created.getCarId());
        
        assertNotNull(found);
        assertEquals("Toyota", found.getCarName());
    }

    @Test
    void testFindByIdNotFound() {
        Car found = carRepository.findById("non-existing-id");
        assertNull(found);
    }

    @Test
    void testUpdateCarFound() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        Car created = carRepository.create(car);
        
        Car updatedCar = new Car();
        updatedCar.setCarName("Toyota Updated");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity(15);
        
        Car result = carRepository.update(created.getCarId(), updatedCar);
        
        assertNotNull(result);
        assertEquals("Toyota Updated", result.getCarName());
        assertEquals("Blue", result.getCarColor());
        assertEquals(15, result.getCarQuantity());
    }

    @Test
    void testUpdateCarNotFound() {
        Car updatedCar = new Car();
        updatedCar.setCarName("Toyota Updated");
        
        Car result = carRepository.update("non-existing-id", updatedCar);
        
        assertNull(result);
    }

    @Test
    void testDeleteCar() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        Car created = carRepository.create(car);
        
        carRepository.delete(created.getCarId());
        
        Car afterDelete = carRepository.findById(created.getCarId());
        assertNull(afterDelete);
    }

    @Test
    void testDeleteNonExistingCar() {
        Car car = new Car();
        car.setCarName("Toyota");
        carRepository.create(car);
        
        int initialSize = 0;
        Iterator<Car> iterator = carRepository.findAll();
        while (iterator.hasNext()) {
            iterator.next();
            initialSize++;
        }
        
        carRepository.delete("non-existing-id");
        
        int finalSize = 0;
        iterator = carRepository.findAll();
        while (iterator.hasNext()) {
            iterator.next();
            finalSize++;
        }
        
        assertEquals(initialSize, finalSize);
    }
}