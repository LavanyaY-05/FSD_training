package com.roadready.Service;

import com.roadready.dto.AddBookingDto;
import com.roadready.dto.BookingByIdRespDto;
import com.roadready.enums.*;
import com.roadready.exceptions.ResourceNotFoundException;
import com.roadready.model.Booking;
import com.roadready.model.Car;
import com.roadready.repository.BookingRepository;
import com.roadready.repository.CarRepository;
import com.roadready.service.BookingService;
import com.roadready.service.CarService;
import org.hibernate.query.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private BookingService bookingService;


    private Booking booking;
    private Car car;

    @BeforeEach
    public void SampleData() {

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

        booking = new Booking();

        booking.setId(200);
        booking.setPickupDateTime(LocalDateTime.parse("2026-12-30T12:00:00"));
        booking.setDropDownDateTime(LocalDateTime.parse("2026-12-31T12:00:00"));

        booking.setPickupLocation("Chennai Central Railway Station");
        booking.setDropdownLocation("Chennai International Airport");

        booking.setDeliveryType(DeliveryType.HOME_DELIVERY);
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        booking.setKycStatus(KycStatus.PENDING);

        booking.setOriginalAmount(new BigDecimal("4500.00"));
        booking.setDiscountedAmount(new BigDecimal("4000.00"));
        booking.setDeliveryCharge(new BigDecimal("50.00"));
        booking.setTotalAmount(new BigDecimal("4050.00"));

        booking.setCancellationInfo(null);

    }

//    @Test
//    public void addBooking(){
//        AddBookingDto dto = new AddBookingDto(LocalDateTime.parse("2026-12-30T12:00:00"), LocalDateTime.parse("2026-12-31T12:00:00"),"Chennai","Chennai",DeliveryType.HOME_DELIVERY.toString(),"");
//        when(carRepository.findById(6)).thenReturn(Optional.of(car));
//        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
//
//        BookingByIdRespDto actualData = bookingService.addBooking(6, "smrithi", dto);
//
//        assertThat(actualData.bookingId()).isEqualTo(booking.getId());    }

    @Test
    public void getById_MustReturnSomething() {

        when(bookingRepository.findById(200)).thenReturn(Optional.of(booking));

        Booking actualBooking = bookingService.getById(200);
        assertThat(actualBooking.getId()).isEqualTo(booking.getId());
        verify(bookingRepository, times(1)).findById(200);
    }

    @Test
    public void getById_MustReturnNothing() {

        when(bookingRepository.findById(200)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookingService.getById(200))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Invalid Booking ID");
        verify(bookingRepository, times(1)).findById(200);
    }


}


