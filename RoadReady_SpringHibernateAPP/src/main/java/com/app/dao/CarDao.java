package com.app.dao;

import com.app.exceptions.ResourseNotFoundException;
import com.app.exceptions.VehicleUnavailableException;
import com.app.model.Car;

public interface CarDao {

    Car getById(int id) throws ResourseNotFoundException;
}
