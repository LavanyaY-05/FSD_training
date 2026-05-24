package com.app;

import com.app.config.AppConfig;
import com.app.dao.CarDao;
import com.app.daoimpl.CarDaoImpl;
import com.app.enums.CarStatus;
import com.app.exceptions.ResourceNotFoundException;
import com.app.model.Car;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.IsolationLevelDataSourceAdapter;

import javax.sql.DataSource;
import java.util.Scanner;

public class App
{
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CarDao carDao = context.getBean(CarDaoImpl.class);

        Scanner s = new Scanner(System.in);
        while(true){
            System.out.println("1. Add Car");
            System.out.println("2. Get all Cars");
            System.out.println("3. Get Car By Id");
            System.out.println("4. Delete Car");
            System.out.println("5. Update Car Status");
            System.out.println("0. exit");
            int op = s.nextInt();
            if(op ==0)
                break;

            switch (op){
                case 1:
                    Car car = new Car();
                    System.out.println("----------- Enter car details --------------");

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

                    carDao.insertCar(car);
                    break;
                case 2:
                    System.out.println("----------------- All Cars -------------------");
                    carDao.getAllCar().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("------------- Get Car By Id -------------");
                    System.out.println("Enter car id : ");
                    int id = s.nextInt();
                    try {
                        System.out.println(carDao.getById(id));
                    }
                    catch (EmptyResultDataAccessException e){
                        System.out.println("Invalid ID....");
                    }
                    break;
                case 4:
                    System.out.println("----------------- Delete Car ----------------");
                    System.out.println("Enter Car Id to delete : ");
                    id = s.nextInt();
                    try{
                        carDao.delete(id);
                    }
                    catch (ResourceNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("----------------Update Car Status---------------");
                    System.out.println("Enter car id:");
                    id = s.nextInt();
                    try{
                        car = carDao.getById(id);
                        System.out.println("Existing record");
                        System.out.println(car);

                        System.out.println("Enter Car Status : ");
                        car.setStatus(CarStatus.valueOf(s.next()));

                        carDao.updateCar(car);
                    } catch (EmptyResultDataAccessException | ResourceNotFoundException e)
                    {
                        System.out.println("Invaild ID");
                    }
                    break;
                default:
                    System.out.println("Invalid Operation");
            }
        }

        context.close();
    }
}
