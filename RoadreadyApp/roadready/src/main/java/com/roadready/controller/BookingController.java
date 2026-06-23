package com.roadready.controller;

import com.roadready.dto.*;
import com.roadready.enums.BookingStatus;
import com.roadready.model.Booking;
import com.roadready.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")

public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/getAllBooking")
    public List<Booking> getAll() {
        return bookingService.getAllBooking();
    }

    @GetMapping("/getAll")
    public BookingPaginationResponse getAllV2(@RequestParam(required = false, defaultValue = "0") int pages, @RequestParam(required = false, defaultValue = "5") int size) {
        return bookingService.getAll(pages, size);
    }


    @GetMapping("/customer")
    public List<BookingCustomerDto> getAllBooking(Principal principal,
                                                  @RequestParam(required = false) String status) {
        String username = principal.getName();
        return bookingService.getAllBookings(username, status);
    }

    @PostMapping("/add/{carId}")
    public BookingByIdRespDto addBooking(@PathVariable int carId, @RequestBody AddBookingDto dto, Principal principal) {
        String username = principal.getName();
        return bookingService.addBooking(carId, username, dto);
    }

    @GetMapping("/hours")
    public long getHours(@RequestParam LocalDateTime pickupDate, @RequestParam LocalDateTime dropdownDate) {
        return bookingService.getHours(pickupDate, dropdownDate);

    }


    @PutMapping("/customer/{id}/cancel")
    public void cancelBooking(@PathVariable int id, Principal principal, @RequestBody CancellationRedDto dto) {
        String username = principal.getName();

        bookingService.cancelBooking(id, username, dto);
    }

    @GetMapping("/getById/{bookingId}")
    public BookingByIdRespDto getBookingById(@PathVariable int bookingId) {
        return bookingService.getBookingById(bookingId);
    }


    // update status
    @PutMapping("/update-kyc-status/{bookingID}")
    public void updateKycStatus(@PathVariable int bookingID, @RequestParam String kycStatus, Principal principal) {
        bookingService.updateKycStatus(bookingID, kycStatus, principal.getName());
    }

    // get by status
    @GetMapping("/bookingstatus")
    public List<BookingStatus> getBookingStatus() {
        return bookingService.getBookingStatus();
    }

    @PostMapping("/confirm/{id}")
    public BookingByIdRespDto setConfirm(@PathVariable int id, Principal principal) {
        return bookingService.setConfirm(id, principal.getName());
    }

}
