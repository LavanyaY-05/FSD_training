package com.roadready.util;

import com.roadready.exceptions.InvalidBookingDateException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class BookingDateUtility {
    public long calculateTime(LocalDateTime pickupDate, LocalDateTime dropdownDate) {
        LocalDateTime today = LocalDateTime.now();

        if (pickupDate.isBefore(today)) {
            throw new InvalidBookingDateException("pickUpDate", "Pick Up date cannot be in the past.");
        }

        if (dropdownDate.isBefore(pickupDate)) {
            throw new InvalidBookingDateException("dropDownDate", "Drop down date must be after the pick up date.");
        }

        // get total minutes
        long minutes = ChronoUnit.MINUTES.between(pickupDate, dropdownDate);
        // calculate hours and roundup it
        long hours = (long) Math.ceil(minutes / 60.0);
        return hours == 0 ? 1 : hours;
    }
}
