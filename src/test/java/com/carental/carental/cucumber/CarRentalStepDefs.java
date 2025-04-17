package com.carental.carental.cucumber;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;

import com.carental.carental.domain.Car;
import com.carental.carental.repository.CarRepository;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Quand;
import io.cucumber.java.fr.Étantdonné;

public class CarRentalStepDefs {

    @Autowired
    private CarRepository carRepository;

    List<Car> cars;
    Car testCar;

    @Étantdonné("des voitures sont disponibles")
    public void desVoituresDisponibles() {
        carRepository.getAllCars().clear(); // reset propre
        carRepository.addCar(new Car("AA123", "Toyota", true));
        carRepository.addCar(new Car("BB456", "BMW", true));
    }

    @Quand("je demande la liste des voitures")
    public void jeDemandeLaListeDesVoitures() {
        cars = carRepository.getAllCars();
    }

    @Alors("toutes les voitures sont affichées")
    public void toutesLesVoituresSontAffichées() {
        assertEquals(2, cars.size());
        assertTrue(cars.stream().allMatch(Car::isAvailable));
    }

    @Étantdonné("une voiture est disponible")
    public void uneVoitureEstDisponible() {
        testCar = new Car("CC789", "Mazda", true);
        carRepository.getAllCars().clear();
        carRepository.addCar(testCar);
    }

    @Quand("je loue cette voiture")
    public void jeLoueCetteVoiture() {
        testCar.setAvailable(false);
        carRepository.updateCar(testCar);
    }

    @Alors("la voiture n'est plus disponible")
    public void laVoitureNEstPlusDisponible() {
        Car car = carRepository.findByRegistrationNumber("CC789").get();
        assertFalse(car.isAvailable());
    }

    @Étantdonné("une voiture est louée")
    public void uneVoitureEstLouee() {
        testCar = new Car("DD999", "Peugeot", false);
        carRepository.getAllCars().clear();
        carRepository.addCar(testCar);
    }

    @Quand("je retourne cette voiture")
    public void jeRetourneCetteVoiture() {
        testCar.setAvailable(true);
        carRepository.updateCar(testCar);
    }

    @Alors("la voiture est marquée comme disponible")
    public void laVoitureEstMarqueeCommeDisponible() {
        Car car = carRepository.findByRegistrationNumber("DD999").get();
        assertTrue(car.isAvailable());
    }
}
