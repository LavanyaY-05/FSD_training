package com.app.daoimpl;

import com.app.dao.BookingDao;
import com.app.dao.CarDao;
import com.app.dao.CustomerDao;
import com.app.enums.BookingStatus;
import com.app.exceptions.InvalidOwnerShipException;
import com.app.exceptions.ResourseNotFoundException;
import com.app.exceptions.VehicleUnavailableException;
import com.app.model.Booking;
import com.app.model.Car;
import com.app.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
public class BookingDaoImpl implements BookingDao {

    @PersistenceContext
    private EntityManager em;

    private CustomerDao customerDao;
    private CarDao carDao;

    

    @Autowired
    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }

    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void insertBooking(Booking booking, String username, Car car) {

        booking.setCar(car);
        booking.setCustomer(customerDao.getCustomerByUsername(username));
        booking.setStatus(BookingStatus.CONFIRMED);

        em.persist(booking);
    }

    @Override
    public List<Booking> getBooking(String username) {
        TypedQuery<Booking> query = em.createQuery("select b from Booking b where b.customer.user.username = ?1", Booking.class );
        query.setParameter(1,username);
        return query.getResultList();
    }

    @Override
    public Booking getBookingById(int id, String username) {
        Booking booking = em.find(Booking.class, id);
        if(booking == null)
            throw new ResourseNotFoundException("Invalid Booking ID......");
        if(!booking.getCustomer().getUser().getUsername().equals(username))
            throw  new InvalidOwnerShipException("You do not own this booking");
        return booking;
    }

    @Override
    public void updateBooking(Booking booking) {

        em.merge(booking);
    }

    @Override
    public void deleteBooking(int id , String username) {
        Booking booking =getBookingById(id,username);
        em.remove(booking);
    }

}
