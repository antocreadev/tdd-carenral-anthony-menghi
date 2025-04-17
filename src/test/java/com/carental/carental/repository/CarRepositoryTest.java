package com.carental.carental.repository;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.carental.carental.domain.Car;


class CarRepositoryTest {

  private CarRepository carRepository;

  @BeforeEach
  void setUp() {
      carRepository = new CarRepository();
  }

  @Test
  void testAddAndFindCar() {
      Car car = new Car("123ABC", "Toyota", true);
      carRepository.addCar(car);

      Optional<Car> result = carRepository.findByRegistrationNumber("123ABC");
      assertTrue(result.isPresent());
      assertEquals("Toyota", result.get().getModel());
  }

  @Test
  void testUpdateCar() {
      Car car = new Car("123ABC", "Toyota", true);
      carRepository.addCar(car);

      car.setAvailable(false);
      carRepository.updateCar(car);

      Optional<Car> result = carRepository.findByRegistrationNumber("123ABC");
      assertTrue(result.isPresent());
      assertFalse(result.get().isAvailable());
  }

  @Test
void testAddCarWhenUnique() {
    Car car = new Car("XYZ123", "Honda", true);
    carRepository.addCar(car);
    Optional<Car> result = carRepository.findByRegistrationNumber("XYZ123");

    assertTrue(result.isPresent());
    assertEquals("Honda", result.get().getModel());
}

  @Test
  void testFindByModel() {
      carRepository.addCar(new Car("REG1", "BMW", true));
      carRepository.addCar(new Car("REG2", "BMW", false));
      carRepository.addCar(new Car("REG3", "Toyota", true));

      List<Car> results = carRepository.findByModel("BMW");

      assertEquals(2, results.size());
      assertTrue(results.stream().allMatch(car -> car.getModel().equals("BMW")));
  }

}