package com.carental.carental.controller;


import com.carental.carental.domain.Car;
import com.carental.carental.service.CarRentalService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarRentalService carRentalService;

    @Test
    void testGetAllCars() throws Exception {
        when(carRentalService.getAllCars())
            .thenReturn(List.of(new Car("123ABC", "Toyota", true)));

        mockMvc.perform(get("/cars"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].registrationNumber").value("123ABC"));
    }

    @Test
    void testRentCar() throws Exception {
        when(carRentalService.rentCar("123ABC")).thenReturn(true);

        mockMvc.perform(post("/cars/rent/123ABC"))
            .andExpect(status().isOk())
            .andExpect(content().string("true"));
    }

    @Test
    void testReturnCar() throws Exception {
        doNothing().when(carRentalService).returnCar("123ABC");

        mockMvc.perform(post("/cars/return/123ABC"))
            .andExpect(status().isOk());
    }
    @Test
void testAddCarEndpoint() throws Exception {
    Car newCar = new Car("ABC789", "Mazda", true);
    when(carRentalService.addCar(any(Car.class))).thenReturn(true);

    mockMvc.perform(post("/cars/add")
            .contentType("application/json")
            .content("""
                {
                    "registrationNumber": "ABC789",
                    "model": "Mazda",
                    "available": true
                }
            """))
        .andExpect(status().isOk())
        .andExpect(content().string("true"));
}

@Test
void testSearchByModelEndpoint() throws Exception {
    List<Car> cars = List.of(new Car("REG1", "Peugeot", true));
    when(carRentalService.searchByModel("Peugeot")).thenReturn(cars);

    mockMvc.perform(get("/cars/search")
            .param("model", "Peugeot"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].registrationNumber").value("REG1"))
        .andExpect(jsonPath("$[0].model").value("Peugeot"));
}

}