package com.carental.carental.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.carental.carental.domain.Car;

@Repository
public class CarRepository {
    private final List<Car> cars = new ArrayList<>();

    public List<Car> getAllCars() {
        return cars;
    }

    public Optional<Car> findByRegistrationNumber(String registrationNumber) {
        return cars.stream()
                .filter(car -> car.getRegistrationNumber().equals(registrationNumber))
                .findFirst();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void updateCar(Car car) {
        cars.replaceAll(c -> c.getRegistrationNumber().equals(car.getRegistrationNumber()) ? car : c);
    }

    public List<Car> findByModel(String model) {
      return cars.stream()
              .filter(car -> car.getModel().equalsIgnoreCase(model))
              .toList();
  }
  
}