package com.carental.carental.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carental.carental.domain.Car;
import com.carental.carental.repository.CarRepository;

@Service
public class CarRentalService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    public boolean rentCar(String registrationNumber) {
        Optional<Car> car = carRepository.findByRegistrationNumber(registrationNumber);
        if (car.isPresent() && car.get().isAvailable()) {
            car.get().setAvailable(false);
            carRepository.updateCar(car.get());
            return true;
        }
        return false;
    }

    public void returnCar(String registrationNumber) {
        Optional<Car> car = carRepository.findByRegistrationNumber(registrationNumber);
        car.ifPresent(c -> {
            c.setAvailable(true);
            carRepository.updateCar(c);
        });
    }
    
    public boolean addCar(Car car) {
      Optional<Car> existing = carRepository.findByRegistrationNumber(car.getRegistrationNumber());
      if (existing.isEmpty()) {
          carRepository.addCar(car);
          return true;
      }
      return false;
  }
  
}