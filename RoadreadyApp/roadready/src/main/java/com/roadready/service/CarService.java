package com.roadready.service;

import com.roadready.Mapper.CarMapper;
import com.roadready.dto.*;
import com.roadready.enums.*;
import com.roadready.exceptions.CarNotAvailableException;
import com.roadready.util.FileValidity;

import com.roadready.exceptions.ResourceNotFoundException;
import com.roadready.model.Car;
import com.roadready.repository.CarRepository;
import com.roadready.util.BookingDateUtility;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final BookingDateUtility bookingDateUtility;
    private final static String UPLOAD_LOC = "C:/Users/LAVANYA/training/RoadReadyApp/public/Images";


    public Car addCar(String username, CarRequestDto requestDto) {
        Car car = CarMapper.mapDtoToEntity(requestDto);
        car.setCreatedBy(username);
        car.setUpdatedBy(username);
        car.setStatus(CarStatus.AVAILABLE);
        car.setIsActive(true);
        return carRepository.save(car);
    }

    public void save(Car car) {
        carRepository.save(car);

    }

    public Car getById(int id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Id...."));
    }

    public CarPaginationDto search(@Valid CarSearchReqDto dto, int page, int size, String sort) {
        bookingDateUtility.calculateTime(dto.pickUpDate(), dto.dropDownDate());

        Pageable pageable = PageRequest.of(page, size);
        pageable = PageRequest.of(page, size);


        Page<Car> cars = carRepository.getCarsBySearches(
                dto.location(),
                dto.pickUpDate(),
                dto.dropDownDate(),
                pageable
        );

        List<CarDetailsResponseDto> filteredCars = cars.stream()
                .map(carMapper::mapCarEntityToDto)
                .toList();

        return new CarPaginationDto(
                cars.getTotalElements(),
                cars.getTotalPages(),
                filteredCars
        );
    }


    public CarPaginationDto searchV2(CarSearchReqDto dto, int page, int size, String sort) {
        // Check validity
        bookingDateUtility.calculateTime(dto.pickUpDate(), dto.dropDownDate());
        List<String> status = List.of("ACTIVE", "CONFIRMED", "PENDING");
        FuelType fuelType = null;
        if (!dto.fuelType().isEmpty())
            fuelType = FuelType.valueOf(dto.fuelType());

        CarTransmission transmission = null;
        if (!dto.transmission().isEmpty())
            transmission = CarTransmission.valueOf(dto.transmission());

        CarType type = null;
        if (!dto.type().isEmpty())
            type = CarType.valueOf(dto.type());

        Pageable pageable;
        if (sort.isEmpty()) {
            pageable = PageRequest.of(page, size);
        } else {
            Sort.Direction direction = sort.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
            pageable = PageRequest.of(page, size, Sort.by(direction, "pricePerHour"));
        }

        Page<Car> cars = carRepository.getCarsBySearch(
                dto.location(),
                dto.pickUpDate(),
                dto.dropDownDate(),
                fuelType,
                transmission,
                type,
                status,
                pageable
        );

        List<CarDetailsResponseDto> filteredCars = cars.stream()
                .map(carMapper::mapCarEntityToDto)
                .toList();

        return new CarPaginationDto(
                cars.getTotalElements(),
                cars.getTotalPages(),
                filteredCars
        );
    }


    public void updateCar(String username, int id, CarRequestDto dto) {
        Car car = getById(id);
        car.setBrand(dto.brand());
        car.setModel(dto.model());
        car.setType(dto.type());
        car.setModelYear(dto.modelYear());
        car.setCarTransmission(dto.carTransmission());
        car.setSeats(dto.seats());
        car.setPricePerHour(dto.PricePerHour());
        car.setFuelType(dto.fuelType());
        car.setMileage(dto.mileage());
        car.setLocation(dto.location());
        car.setUpdatedBy(username);
        save(car);
    }

    public void deleteCar(int id, String name) {
        Car car = getById(id);
        car.setIsActive(false);
        car.setUpdatedBy(name);
        carRepository.save(car);

    }

    public CarDetailsResponseDto getCarById(int id) {
        Car car = getById(id);
        return carMapper.mapCarEntityToDto(car);
    }

    public CarPaginationDto browseCars(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Car> cars = carRepository.findAll(pageable);
        List<CarDetailsResponseDto> filteredCars = cars.stream().map(carMapper::mapCarEntityToDto)
                .toList();

        return carMapper.mapEntitytoDto(filteredCars, cars);
    }

    public List<Car> getCarByStatus(CarStatus status) {
        return carRepository.findByStatus(status);
    }


    public List<String> getLocations() {
        return carRepository.getLocations();
    }


    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public CarPaginationDto getAllV2(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Car> list = carRepository.findAllByIsActive(true, pageable);
        List<CarDetailsResponseDto> filteredCars = list.stream()
                .map(carMapper::mapCarEntityToDto)
                .toList();


        return new CarPaginationDto(
                list.getTotalElements(),
                list.getTotalPages(),
                filteredCars
        );
    }

    public CarTypeRespDto getTypes() {
        return new CarTypeRespDto(
                Arrays.stream(CarType.values()).toList(),
                Arrays.stream(CarTransmission.values()).toList(),
                Arrays.stream(FuelType.values()).toList()
        );
    }

    public String uploadImage(MultipartFile file) throws IOException {
        FileValidity.validateFile(file);
        String filename = file.getOriginalFilename();
        Path uploadPath = Paths.get(UPLOAD_LOC);
        Path destinationPath = uploadPath.resolve(filename);
        Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
        return filename;

    }

    public List<String> getAddress() {
        return carRepository.getAddress();
    }

    public List<StatDto> getCarStats() {
        return carRepository.getCarStats();
    }

    public Car getAvailableCarById(int carId, LocalDateTime pickupDateTime, LocalDateTime dropdownDateTime) {
        List<String> status = List.of("ACTIVE", "CONFIRMED", "PENDING");
        return carRepository.findAvailableCarById(carId, pickupDateTime, dropdownDateTime, status)
                .orElseThrow(() -> new CarNotAvailableException("Car is not available for the selected dates/times"));
    }


    public List<Car> getAll() {
        return carRepository.findAll();
    }
}
