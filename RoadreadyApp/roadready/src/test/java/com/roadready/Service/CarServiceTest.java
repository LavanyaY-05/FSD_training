package com.roadready.Service;

import com.roadready.dto.CarRequestDto;
import com.roadready.enums.CarTransmission;
import com.roadready.enums.CarType;
import com.roadready.enums.FuelType;
import com.roadready.exceptions.ResourceNotFoundException;
import com.roadready.model.Car;
import com.roadready.repository.CarRepository;
import com.roadready.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;


    private Car car, car2, car3;

    @BeforeEach
    public void sampleData() {
        car = new Car();
        car.setId(1000);
        car.setBrand("Toyota");
        car.setModel("Camery");
        car.setType(CarType.HATCHBACK);
        car.setModelYear(2024);
        car.setCarTransmission(CarTransmission.AUTOMATIC);
        car.setSeats(5);
        car.setPricePerHour(BigDecimal.valueOf(150.0));
        car.setFuelType(FuelType.DIESEL);
        car.setMileage(150);
        car.setLocation("Chennai");

        car2 = new Car();
        car2.setId(1001);
        car2.setBrand("Mahindra");
        car2.setModel("XUV700");
        car2.setType(CarType.SUV);
        car2.setModelYear(2024);
        car2.setCarTransmission(CarTransmission.AUTOMATIC);
        car2.setSeats(7);
        car2.setPricePerHour(BigDecimal.valueOf(350.0));
        car2.setFuelType(FuelType.DIESEL);
        car2.setMileage(12000);
        car2.setLocation("Bangalore");

        car3 = new Car();
        car3.setId(1002);
        car3.setBrand("Tata");
        car3.setModel("EV");
        car3.setType(CarType.SEDAN);
        car3.setModelYear(2023);
        car3.setCarTransmission(CarTransmission.AUTOMATIC);
        car3.setSeats(5);
        car3.setPricePerHour(BigDecimal.valueOf(200.0));
        car3.setFuelType(FuelType.ELECTRIC);
        car3.setMileage(5000);
        car3.setLocation("Mumbai");

    }

    @Test
    public void addCar_MustReturnCar() {
        CarRequestDto dto = new CarRequestDto("Hyundai", "i20", CarType.HATCHBACK, 2023, CarTransmission.AUTOMATIC, FuelType.DIESEL, 5, 16.5, BigDecimal.valueOf(150.00), "Chennai", "afefe", "erec");

        when(carRepository.save(any(Car.class))).thenReturn(car);
        Car carData = carService.addCar("park", dto);

        assertThat(carData.getId()).isEqualTo(car.getId());
        verify(carRepository, times(1)).save(any(Car.class));
    }

    @Test
    public void getAll_MustReturnCarList() {
        when(carRepository.findAll()).thenReturn(List.of(car, car2, car3));
        assertThat(carService.findAll()).hasSize(3);
        verify(carRepository, times(1)).findAll();

    }

    @Test
    public void getAll_MustReturnEmptyList() {
        when(carRepository.findAll()).thenReturn(List.of());
        assertThat(carService.findAll()).hasSize(0);
        verify(carRepository, times(1)).findAll();

    }

    @Test
    public void delete_MustNotReturnNothing() {
        when(carRepository.findById(1000)).thenReturn(Optional.of(car));
//        doNothing().when(carRepository).save(car);
        carService.deleteCar(1000, "park");

        verify(carRepository, times(1)).findById(1000);
        verify(carRepository, times(1)).save(car);

    }

    @Test
    public void getById_ReturnCar() {
        when(carRepository.findById(1000)).thenReturn(Optional.of(car));
        Car actualCall = carService.getById(1000);
        assertThat(actualCall.getId()).isEqualTo(car.getId());
        verify(carRepository, times(1)).findById(1000);

    }

    @Test
    public void getById_ReturnError() {
        when(carRepository.findById(1000)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> carService.getById(1000))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Invalid Id....");
        verify(carRepository, times(1)).findById(1000);

    }
}
