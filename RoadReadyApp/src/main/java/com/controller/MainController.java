package com.controller;
import com.ResourseNotFoundException;
import com.config.HibernateConfig;
import com.enums.BookingStatus;
import com.enums.CarStatus;
import com.model.Booking;
import com.model.Car;
import com.model.Customer;
import com.model.User;
import com.service.*;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import java.util.List;
import java.util.Scanner;

public class MainController
{
    public static void main(String[] args) {
       Session session = HibernateConfig.getSessionFactory().openSession();

        AuthService authService = new AuthService(session);
        CustomerService customerService = new CustomerService(session);
        CarService carService = new CarService(session);
        BookingService bookingService = new BookingService(session);

        Scanner s = new Scanner(System.in);
        System.out.println("Enter Username:");
        String username = s.next();
        System.out.println("Enter Password:");
        String password = s.next();
       try{
           User user = authService.login(username, password);

           switch(user.getRole().toString()){
               case "CUSTOMER":
                   System.out.println("Customer");
                   while(true){
                       System.out.println("1. Edit Profile");
                       System.out.println("2. Display Profile");
                       System.out.println("3. Fetch all Cars");
                       System.out.println("4. Book Car");
                       System.out.println("5. Fetch all Bookings");
                       System.out.println("0. exit");

                       int op = s.nextInt();
                       if(op ==0){
                           break;
                       }
                       switch(op){
                           case 1:
                               Customer customer = new Customer();
                               System.out.println("------- Edit Profile ----------");
                               s.nextLine();
                               System.out.println("Enter First Name");
                               customer.setFirstName(s.next());
                               System.out.println("Enter Last Name");
                               customer.setLastName(s.next());
                               System.out.println("Enter Email:");
                               customer.setEmail(s.next());
                               System.out.println("Enter Phone Number:");
                               customer.setPhoneNumber(s.next());
                               customerService.UpdateProfile(customer);
                               System.out.println("Profile Updated");
                               break;

                           case 2:
                               System.out.println("---------------Displaying User Profile---------------");
                               customer = customerService.getCustomer(username);
                               System.out.println(customer);
                               break;
                           case 3:
                               System.out.println("------------ Fetching all cars -------------");
                               List<Car> cars = carService.getAllCars();
                               cars.forEach(System.out::println);
                               break;
                           case 4:
                               System.out.println("--------- Booking Car -----------");
                               // get carId
                               System.out.println("Enter Car Id:");
                               int id = s.nextInt();
                               try{
                                   Car car = carService.getCarById(id);
                                   if(car.getStatus().toString().equals("AVAILABLE")) {
                                       Booking booking = new Booking();
                                       System.out.println("Enter Pick Up Date and Time");
                                       s.nextLine();
                                       booking.setPickupDateTime(s.nextLine());
                                       System.out.println("Enter Drop Down Date and Time");
                                       booking.setDropOffDateTime(s.nextLine());

                                       System.out.println("Enter pick up location");
                                       booking.setPickUpLocation(s.next());

                                       System.out.println("Enter Drop Down location");
                                       booking.setDropOffLocation(s.next());

                                       booking.setStatus(BookingStatus.CONFIRMED);
                                       booking.setCustomer(customerService.getCustomer(username));
                                       booking.setCar(car);
                                       bookingService.addBooking(booking);
                                       System.out.println("Car Booked....");
                                   }
                               else{
                                   System.out.println("Car is not available. try another car");
                               }}
                               catch(ResourseNotFoundException e){

                                       System.out.println(e.getMessage());
                                   }
                               break;
                           case 5:
                               System.out.println("----------- Your Bookings --------------");
                               List<Booking> bookings = bookingService.getBookingByUsername(username);
                               bookings.forEach(System.out::println);
                               break;
                       }

                   }
                   break;
               case "ADMIN":
                   System.out.println("ADMIN");
                   while(true) {
                       System.out.println("1. Add Car");
                       System.out.println("2. View All Car");
                       System.out.println("3. Fetch car by Id");
                       System.out.println("4. Update Car");
                       System.out.println("5. Delete Car");
                       System.out.println("0. exit");

                       int op = s.nextInt();
                       if (op == 0) {
                           break;
                       }

                       try{
                           switch (op) {
                               case 1:
                                   System.out.println("---------- ADD CAR ------------");
                                   Car car = new Car();
                                   System.out.println("Enter Car Brand :");
                                   car.setBrand(s.next());
                                   System.out.println("Enter Car Model :");
                                   car.setModel(s.next());
                                   System.out.println("Enter type :");
                                   car.setType(s.next());
                                   System.out.println("Enter Fuel Type :");
                                   car.setFuelType(s.next());
                                   System.out.println("Enter Number Of Seats :");
                                   car.setSeats(s.nextInt());
                                   System.out.println("Enter Price Per Day :");
                                   car.setPricePerDay(s.nextDouble());
                                   System.out.println("Enter Car Status");
                                   car.setStatus(CarStatus.valueOf(s.next()));

                                   carService.addCar(car);
                                   System.out.println("Car Added");
                                   break;
                               case 2:
                                   System.out.println("------------ View all cars -------------");
                                   List<Car> cars = carService.getAllCars();
                                   cars.forEach(System.out::println);
                                   break;
                               case 3:
                                   System.out.println("------------- View Car By Id -----------------");
                                   System.out.println("Enter car id:");
                                   int id = s.nextInt();
                                   System.out.println(carService.getCarById(id));
                                   break;
                               case 4:
                                   System.out.println("--------------- Update Car ---------------");
                                   System.out.println("Enter car Id: ");
                                   id = s.nextInt();
                                   car = carService.getCarById(id);
                                   System.out.println("Existing Record\n" + car);
                                   System.out.println("Enter Car Brand :");
                                   car.setBrand(s.next());
                                   System.out.println("Enter Car Model :");
                                   car.setModel(s.next());
                                   System.out.println("Enter type :");
                                   car.setType(s.next());
                                   System.out.println("Enter Fuel Type :");
                                   car.setFuelType(s.next());
                                   System.out.println("Enter Number Of Seats :");
                                   car.setSeats(s.nextInt());
                                   System.out.println("Enter Price Per Day :");
                                   car.setPricePerDay(s.nextDouble());
                                   System.out.println("Enter Car Status");
                                   car.setStatus(CarStatus.valueOf(s.next()));
                                   carService.updateCar(car);
                                   System.out.println("Car Updated");
                                   break;
                               case 5:
                                   System.out.println("Enter car Id: ");
                                   id = s.nextInt();
                                   car = carService.getCarById(id);
                                   carService.deleteCar(car);
                                   System.out.println("Car Deleted.");
                                   break;

                           }
                       }
                       catch (ResourseNotFoundException e){
                           System.out.println(e.getMessage());
                       }

                   }

                   break;

           }

       }
       catch (NoResultException e){
           System.out.println("Invalid Credentials!!!");
       }
       s.close();
       session.close();
    }
}
