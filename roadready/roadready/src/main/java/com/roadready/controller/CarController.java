package com.roadready.controller;

import com.roadready.exceptions.ResourseNotFoundException;
import com.roadready.model.Car;
import com.roadready.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CarController {

    private CarService carService;

    @GetMapping("/api/car/all")
    public List<Car> getAll(){
        return carService.getAll();
    }

    @PostMapping("/api/car/add")
    public void addCar(@RequestBody Car car){
        carService.addCar(car);
    }

    @GetMapping("/api/car/get-one/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id){
        try{
            Car car = carService.getById(id);
            return ResponseEntity.ok(car);
        }
        catch (ResourseNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/api/car/update/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable int id,
                          @RequestBody Car car){
        try{
            carService.updateCar(id, car);
            return ResponseEntity.ok().build();
        }
        catch (ResourseNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/api/car/delete/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable int id){
        try{
            carService.deleteCar(id);
            return ResponseEntity.ok().build();
        }
        catch (ResourseNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}


