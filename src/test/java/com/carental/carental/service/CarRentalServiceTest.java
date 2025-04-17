package com.carental.carental.service;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.carental.carental.domain.Car;
import com.carental.carental.repository.CarRepository;

class CarRentalServiceTest {

  @InjectMocks
  private CarRentalService carRentalService;

  @Mock
  private CarRepository carRepository;

  private AutoCloseable closeable;

  @BeforeEach
  void init() {
      closeable = MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAllCars() {
      List<Car> mockCars = List.of(new Car("123ABC", "Toyota", true));
      when(carRepository.getAllCars()).thenReturn(mockCars);

      List<Car> result = carRentalService.getAllCars();
      assertEquals(1, result.size());
      verify(carRepository).getAllCars();
  }

  @Test
  void testRentCarSuccess() {
      Car car = new Car("123ABC", "Toyota", true);
      when(carRepository.findByRegistrationNumber("123ABC")).thenReturn(Optional.of(car));

      boolean result = carRentalService.rentCar("123ABC");

      assertTrue(result);
      assertFalse(car.isAvailable());
      verify(carRepository).updateCar(car);
  }

  @Test
  void testRentCarFailure_NotAvailable() {
      Car car = new Car("123ABC", "Toyota", false);
      when(carRepository.findByRegistrationNumber("123ABC")).thenReturn(Optional.of(car));

      boolean result = carRentalService.rentCar("123ABC");

      assertFalse(result);
      verify(carRepository, never()).updateCar(any());
  }

  @Test
  void testReturnCar() {
      Car car = new Car("123ABC", "Toyota", false);
      when(carRepository.findByRegistrationNumber("123ABC")).thenReturn(Optional.of(car));

      carRentalService.returnCar("123ABC");

      assertTrue(car.isAvailable());
      verify(carRepository).updateCar(car);
  }
  @Test
void testAddCarSuccess() {
    Car newCar = new Car("NEW123", "Ford", true);
    when(carRepository.findByRegistrationNumber("NEW123")).thenReturn(Optional.empty());

    boolean result = carRentalService.addCar(newCar);

    assertTrue(result);
    verify(carRepository).addCar(newCar);
}

@Test
void testAddCarFailsIfExists() {
    Car existingCar = new Car("EXIST123", "BMW", true);
    when(carRepository.findByRegistrationNumber("EXIST123")).thenReturn(Optional.of(existingCar));

    boolean result = carRentalService.addCar(existingCar);

    assertFalse(result);
    verify(carRepository, never()).addCar(any());
}

@Test
void testSearchByModel() {
    List<Car> cars = List.of(
        new Car("1", "BMW", true),
        new Car("2", "BMW", false)
    );
    when(carRepository.findByModel("BMW")).thenReturn(cars);

    List<Car> result = carRentalService.searchByModel("BMW");

    assertEquals(2, result.size());
    verify(carRepository).findByModel("BMW");
}

}