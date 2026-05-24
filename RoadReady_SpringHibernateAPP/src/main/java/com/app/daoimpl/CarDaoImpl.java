package com.app.daoimpl;

import com.app.dao.CarDao;
import com.app.exceptions.ResourseNotFoundException;
import com.app.exceptions.VehicleUnavailableException;
import com.app.model.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class CarDaoImpl implements CarDao {

    @PersistenceContext
    private EntityManager em;
    @Override
    public Car getById(int id) throws ResourseNotFoundException {
        Car car = em.find(Car.class, id);
        if(car == null)
            throw new ResourseNotFoundException("Invalid Id");
        if(!car.getStatus().toString().equals("AVAILABLE"))
            throw new VehicleUnavailableException("Car is not AVAILABLE, Kindly select another car");
        return car;
    }

}
