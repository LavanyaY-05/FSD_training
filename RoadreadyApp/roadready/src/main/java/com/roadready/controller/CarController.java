package com.roadready.controller;

import com.roadready.dto.*;
import com.roadready.enums.CarStatus;
import com.roadready.model.Car;
import com.roadready.service.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/car")
@CrossOrigin(origins = "http://localhost:5173/")

public class CarController {

    private CarService carService;

    @PostMapping("/searchCars")
    public CarPaginationDto search(@Valid @RequestBody CarSearchReqDto dto,
                                   @RequestParam(required = false, defaultValue = "0") int page,
                                   @RequestParam(required = false, defaultValue = "10") int size,
                                   @RequestParam(required = false, defaultValue = "") String sort
    ) {
        return carService.search(dto, page, size, sort);
    }

    @PostMapping("/search")
    public CarPaginationDto searchV2(@Valid @RequestBody CarSearchReqDto dto,
                                     @RequestParam(required = false, defaultValue = "0") int page,
                                     @RequestParam(required = false, defaultValue = "10") int size,
                                     @RequestParam(required = false, defaultValue = "") String sort
    ) {
        return carService.searchV2(dto, page, size, sort);
    }

    @GetMapping("/locations")
    public List<String> getLocations() {
        return carService.getLocations();
    }

    @GetMapping("/address")
    public List<String> getAddress() {
        return carService.getAddress();
    }

    @GetMapping("/getall")
    public List<Car> getAll() {
        return carService.getAll();
    }

    @GetMapping("/all")
    public CarPaginationDto getAllV2(@RequestParam(required = false, defaultValue = "0") int page,
                                     @RequestParam(required = false, defaultValue = "10") int size) {
        return carService.getAllV2(page, size);
    }

    @GetMapping("/get/{id}")
    public CarDetailsResponseDto getById(@PathVariable int id) {
        return carService.getCarById(id);
    }

    @PostMapping("/add")
    public Car addCar(Principal principal, @Valid @RequestBody CarRequestDto requestDto) {
        return carService.addCar(principal.getName(), requestDto);
    }

    // update
    @PutMapping("/update/{id}")
    public void updateCar(@PathVariable int id,
                          @Valid @RequestBody CarRequestDto requestDto,
                          Principal principal) {
        carService.updateCar(principal.getName(), id, requestDto);
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public void deleteCar(@PathVariable int id, Principal principal) {
        carService.deleteCar(id, principal.getName());
    }


    // get by status
    @GetMapping("/getByStatus")
    public List<Car> getCarByStatus(CarStatus status) {
        return carService.getCarByStatus(status);
    }

    @GetMapping("/getTypes")
    public CarTypeRespDto getTypes() {
        return carService.getTypes();
    }


    // get all with structured response pattern
    @GetMapping("/browseCars")
    public CarPaginationDto browseCars(@RequestParam(defaultValue = "0", required = false) int page,
                                       @RequestParam(defaultValue = "10", required = false) int size) {
        return carService.browseCars(page, size);
    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {

        return carService.uploadImage(file);
    }


}


