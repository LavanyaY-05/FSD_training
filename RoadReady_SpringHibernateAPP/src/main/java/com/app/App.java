package com.app;

import com.app.config.AppConfig;
import com.app.dao.AuthDao;
import com.app.dao.BookingDao;
import com.app.dao.CarDao;
import com.app.dao.CustomerDao;
import com.app.exceptions.InvalidOwnerShipException;
import com.app.exceptions.ResourseNotFoundException;
import com.app.exceptions.VehicleUnavailableException;
import com.app.model.Booking;
import com.app.model.Car;
import com.app.model.User;
import jakarta.persistence.NoResultException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        AuthDao authDao = context.getBean(AuthDao.class);
        CarDao carDao = context.getBean(CarDao.class);
        CustomerDao customerDao = context.getBean(CustomerDao.class);
        BookingDao bookingDao = context.getBean(BookingDao.class);
        Scanner s = new Scanner(System.in);

        DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("----------------- LOGIN -------------------");
        System.out.println("Enter username :");
        String username = s.next();
        System.out.println("Enter password :");
        String password = s.next();

        try{
            User user = authDao.login(username, password);
            System.out.println("Welcome : "+ username);

            while(true){
                System.out.println("1. Add Booking");
                System.out.println("2. Fetch All Booking");
                System.out.println("3. Update Booking");
                System.out.println("4. Delete Booking");
                System.out.println("0. Exit");

                int op = s.nextInt();
                if(op == 0)
                    break;

                switch (op){
                    case 1:
                        System.out.println("Enter car id : ");
                        int id = s.nextInt();
                        try{
                            Car car = carDao.getById(id);
                            Booking booking = new Booking();
                            s.nextLine();
                            System.out.println("Enter Pickup Date and Time: ");
                            LocalDateTime pickUpDateTime = LocalDateTime.parse(s.nextLine(), formatter);
                            booking.setPickupDateTime(pickUpDateTime);
                            System.out.println("Enter Drop Down Date and Time: ");
                            LocalDateTime dropDownDateTime = LocalDateTime.parse(s.nextLine(), formatter);
                            booking.setDropOffDateTime(dropDownDateTime);
                            System.out.println("Enter Pickup Location: ");
                            booking.setPickUpLocation(s.nextLine());
                            System.out.println("Enter Drop Down Location: ");
                            booking.setDropOffLocation(s.nextLine());
                            long days = ChronoUnit.DAYS.between(pickUpDateTime,dropDownDateTime);
                            booking.setTotalAmount(days * car.getPricePerDay());

                            bookingDao.insertBooking(booking, username, car);
                            System.out.println("Car Booked.");

                        }
                        catch (VehicleUnavailableException | ResourseNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("-------------- All Bookings ------------------");
                        bookingDao.getBooking(username).forEach(System.out::println);
                        break;
                    case 3:
                        System.out.println("------------ Update Booking ---------------");
                        System.out.println("Enter Booking id : ");
                        id = s.nextInt();
                        try{
                           Booking booking = bookingDao.getBookingById(id,username );
                            System.out.println("Existing Record: \n" + booking);
                            s.nextLine();
                            System.out.println("Enter Pickup Date and Time: ");
                            LocalDateTime pickUpDateTime = LocalDateTime.parse(s.nextLine(), formatter);
                            booking.setPickupDateTime(pickUpDateTime);
                            System.out.println("Enter Drop Down Date and Time: ");
                            LocalDateTime dropDownDateTime = LocalDateTime.parse(s.nextLine(), formatter);
                            booking.setDropOffDateTime(dropDownDateTime);
                            System.out.println("Enter Pickup Location: ");
                            booking.setPickUpLocation(s.nextLine());
                            System.out.println("Enter Drop Down Location: ");
                            booking.setDropOffLocation(s.nextLine());

                            bookingDao.updateBooking(booking);

                            System.out.println("Booking updated");

                        }
                        catch(ResourseNotFoundException | InvalidOwnerShipException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.println("------------ Delete Booking ---------------");
                        System.out.println("Enter Booking id : ");
                        id = s.nextInt();
                        try{
                            bookingDao.deleteBooking(id, username);
                            System.out.println("Booking removed");
                        }
                        catch(ResourseNotFoundException | InvalidOwnerShipException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    default :
                        System.out.println("Invalid Operation");
                }

            }
        }
        catch(NoResultException e){
            System.out.println("Invalid Credentials");
        }


        context.close();
    }
}
