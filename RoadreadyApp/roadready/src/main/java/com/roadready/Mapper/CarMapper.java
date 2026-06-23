package com.roadready.Mapper;

import com.roadready.dto.CarDetailsResponseDto;
import com.roadready.dto.CarPaginationDto;
import com.roadready.dto.CarRequestDto;

import com.roadready.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarMapper {
    public static Car mapDtoToEntity(CarRequestDto dto) {
        Car car = new Car();
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
        car.setAddress(dto.address());
        car.setImageUrl(dto.image());
        return car;
    }

    public CarDetailsResponseDto mapCarEntityToDto(Car car) {
        return new CarDetailsResponseDto(

                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getType(),
                car.getModelYear(),
                car.getFuelType(),
                car.getCarTransmission(),
                car.getSeats(),
                car.getPricePerHour(),
                car.getLocation(),
                car.getAddress(),
                car.getImageUrl()


        );

    }

    public CarPaginationDto mapEntitytoDto(List<CarDetailsResponseDto> filteredCars, Page<Car> cars) {
        return new CarPaginationDto(
                cars.getTotalElements(),
                cars.getTotalPages(),
                filteredCars
        );
    }
}
