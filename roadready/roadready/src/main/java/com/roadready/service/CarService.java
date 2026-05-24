package com.roadready.service;

import com.roadready.exceptions.ResourseNotFoundException;
import com.roadready.model.Car;
import com.roadready.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarService {

    private CarRepository carRepository;

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public void addCar(Car car) {
        carRepository.save(car);
    }

    public Car getById(int id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Invalid Id...."));
    }

    public void updateCar(int id, Car car) {
        Car existingCar = getById(id);
        existingCar.setBrand(car.getBrand());
        existingCar.setModel(car.getModel());
        existingCar.setType(car.getType());
        existingCar.setFuelType(car.getFuelType());
        existingCar.setStatus(car.getStatus());
        existingCar.setPricePerDay(car.getPricePerDay());
        existingCar.setSeats(car.getSeats());

        carRepository.save(existingCar);
    }

    public void deleteCar(int id) {
        getById(id);
        carRepository.deleteById(id);
    }
}
