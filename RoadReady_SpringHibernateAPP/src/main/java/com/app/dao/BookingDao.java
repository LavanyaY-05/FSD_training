package com.app.dao;

import com.app.model.Booking;
import com.app.model.Car;
import com.app.model.Customer;

import java.util.List;

public interface BookingDao {

    void insertBooking(Booking booking, String username , Car car);

    List<Booking> getBooking(String username);

    Booking getBookingById(int id, String username);

    void updateBooking(Booking booking);

    void deleteBooking(int id, String username);
}
