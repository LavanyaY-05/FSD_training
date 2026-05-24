package com.service;

import com.ResourseNotFoundException;
import com.model.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CarService {
    private Session session;

    public CarService(Session session) {
        this.session = session;
    }
    public List<Car> getAllCars() {
        Transaction tx = session.beginTransaction();
        List<Car> cars= session.createQuery("from Car", Car.class)
                        .getResultList();
        tx.commit();
        return cars;
    }

    public Car getCarById(int id) {
        Transaction tx = session.beginTransaction();
//        Car car = session.createQuery("from Car where id =: id", Car.class)
//                        .setParameter("id",id)
//                                .getSingleResult();
        Car car = session.find(Car.class, id);
        tx.commit();
        if(car == null)
            throw new ResourseNotFoundException("Invalid ID..");
        return car;
    }

    public void addCar(Car car) {
        Transaction tx = session.beginTransaction();
        session.persist(car);
        tx.commit();
    }

    public void updateCar(Car car) {
        Transaction tx = session.beginTransaction();
        session.persist(car);
        tx.commit();
    }

    public void deleteCar(Car car) {

        Transaction tx = session.beginTransaction();
        session.remove(car);
        tx.commit();
    }
}
