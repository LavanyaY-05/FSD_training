package com.service;

import com.model.Booking;
import com.model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookingService
{
    private Session session;
    private  CustomerService customerService;
    public BookingService(Session session) {
        this.session = session;
        customerService = new CustomerService(session);
    }

    public List<Booking> getBookingByUsername(String username) {
        Customer customer = customerService.getCustomer(username);
        Transaction tx = session.beginTransaction();
        List<Booking> bookings = session.createQuery("select b from Booking b where b.customer.id =: id", Booking.class)
                .setParameter("id",customer.getId())
                        .getResultList();

        tx.commit();
        return bookings;
    }

    public void addBooking(Booking booking) {
        Transaction tx = session.beginTransaction();
        session.persist(booking);
        tx.commit();
    }
}
