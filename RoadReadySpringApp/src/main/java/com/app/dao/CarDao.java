package com.app.dao;

import com.app.exceptions.ResourceNotFoundException;
import com.app.model.Car;

import java.util.List;

public interface CarDao
{
    void insertCar(Car car);
    List<Car> getAllCar();
    Car getById(int id) throws ResourceNotFoundException;
    void updateCar(Car car);
    void delete(int id) throws ResourceNotFoundException;
}
