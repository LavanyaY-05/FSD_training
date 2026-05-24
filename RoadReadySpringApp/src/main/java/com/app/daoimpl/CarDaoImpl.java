package com.app.daoimpl;

import com.app.dao.CarDao;
import com.app.enums.CarStatus;
import com.app.exceptions.ResourceNotFoundException;
import com.app.model.Car;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarDaoImpl implements CarDao {
    private JdbcTemplate jdbcTemplate;

    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Car> mapper(){
        return ((rs,num) ->
                new Car(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("type"),
                        rs.getString("fuelType"),
                        rs.getInt("seats"),
                        rs.getDouble("PricePerDay"),
                        CarStatus.valueOf(rs.getString("status"))
                ));
    }
    @Override
    public void insertCar(Car car) {
       String sql = "insert into car (PricePerDay, brand, fuelType, model, seats, status, type) values (?,?,?,?,?,?,?)";
       jdbcTemplate.update(sql,
                car.getPricePerDay(),
               car.getBrand(),
               car.getFuelType(),
               car.getModel(),
               car.getSeats(),
               car.getStatus().toString(),
               car.getType());
        System.out.println(" Car Inserted");
    }

    @Override
    public List<Car> getAllCar() {
        String sql = "select * from car";
        return jdbcTemplate.query(sql,mapper());
    }

    @Override
    public Car getById(int id) throws ResourceNotFoundException {
        String sql = "select * from car where id = ?";
        return jdbcTemplate.queryForObject(sql, mapper(), id);
    }

    @Override
    public void updateCar(Car car) throws ResourceNotFoundException{

        String sql = "update car set status =? where id =?";
        jdbcTemplate.update(sql, car.getStatus().toString(), car.getId());
        System.out.println("Car Updated");

    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
       String sql = "delete from car where id = ?";
       int rows = jdbcTemplate.update(sql,id);
       if(rows == 0)
           throw new ResourceNotFoundException("Invalid ID");
        System.out.println("Car Deleted");
    }
}
