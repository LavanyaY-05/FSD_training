package com.roadready.service;

import com.roadready.Mapper.VehicleInspectionMapper;
import com.roadready.dto.InspectionReqDto;
import com.roadready.enums.BookingStatus;
import com.roadready.enums.CarStatus;
import com.roadready.enums.InspectionType;
import com.roadready.exceptions.IllegalBookingStatusException;
import com.roadready.model.Agent;
import com.roadready.model.Booking;
import com.roadready.model.Car;
import com.roadready.model.VehicleInspection;
import com.roadready.repository.VehicleInspectionRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VehicleInspectionService {

    private final VehicleInspectionMapper inspectionMapper;
    private final BookingService bookingService;
    private final AgentService agentService;
    private final CarService carService;
    private final VehicleInspectionRepository inspectionRepository;


    public void add(int id, @Valid InspectionReqDto dto, String name) {

        Booking booking = bookingService.getById(id);
        VehicleInspection inspection = inspectionMapper.mapDtoToEntity(dto);
        Agent agent = agentService.findByUsername(name);
        Car car = booking.getCar();

        if (inspection.getInspectionType().equals(InspectionType.PRE_RENT)) {
            if (!booking.getBookingStatus().equals(BookingStatus.CONFIRMED))
                throw new IllegalBookingStatusException("Pre-rental inspection requires a CONFIRMED booking");
            booking.setBookingStatus(BookingStatus.ACTIVE);
            car.setStatus(CarStatus.BOOKED);
        } else if (inspection.getInspectionType().equals(InspectionType.POST_RENT)) {
            if (!booking.getBookingStatus().equals(BookingStatus.ACTIVE))
                throw new IllegalBookingStatusException("Post-rental inspection requires an ACTIVE booking");
            if (inspection.isHasDamage()) {
                car.setStatus(CarStatus.MAINTENANCE);

            } else {
                car.setStatus(CarStatus.AVAILABLE);
            }
            booking.setBookingStatus(BookingStatus.COMPLETED);
        }
        carService.save(car);
        inspection.setAgent(agent);
        inspection.setBooking(booking);
        bookingService.save(booking);
        inspectionRepository.save(inspection);

    }


}
