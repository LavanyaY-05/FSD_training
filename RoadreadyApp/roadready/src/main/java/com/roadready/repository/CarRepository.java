package com.roadready.repository;

import com.roadready.dto.StatDto;
import com.roadready.enums.CarStatus;
import com.roadready.enums.CarTransmission;
import com.roadready.enums.CarType;
import com.roadready.enums.FuelType;
import com.roadready.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findByStatus(CarStatus status);

    @Query("""
            select c
                     from Car c
                     where c.id NOT IN (
                            select b.car.id from Booking b
                            where b.dropDownDateTime > :pickUpDate
                            AND b.pickupDateTime < :dropDownDate
                            AND b.bookingStatus IN ('ACTIVE','CONFIRMED','PENDING')
                     )
            
                     AND c.location = :location""")
    Page<Car> getCarsBySearches(String location, LocalDateTime pickUpDate, LocalDateTime dropDownDate, Pageable pageable);

    @Query("""
             select c
             from Car c
             where c.id NOT IN (
                    select b.car.id from Booking b
                    where b.dropDownDateTime > :pickUpDate
                    AND b.pickupDateTime < :dropDownDate
                    AND b.bookingStatus IN (:status)
             )
            
             AND c.location = :location
             AND c.status = AVAILABLE
             AND (:fuelType is NULL or c.fuelType = :fuelType)
             AND (:transmission is NULL or c.carTransmission = :transmission)
             AND (:type is NULL or c.type = :type)
            
            """)
    Page<Car> getCarsBySearch(
            String location,
            LocalDateTime pickUpDate,
            LocalDateTime dropDownDate,
            FuelType fuelType,
            CarTransmission transmission,
            CarType type,
            List<String> status, Pageable pageable);


    @Query("""
                       select distinct location
                        from Car
            """)
    List<String> getLocations();

    Page<Car> findAllByIsActive(boolean active, Pageable pageable);

    @Query("""
                       select distinct address
                        from Car
            """)
    List<String> getAddress();

    @Query("""
             select c.location as name , count(c.id) as total
                         from Car c
            group by c.location
            """)
    List<StatDto> getCarStats();

    @Query("""
            select c from Car c
            where c.id = ?1
            and c.id not in (
            select b.car.id from Booking b
                           where b.dropDownDateTime > ?2
                           and b.pickupDateTime < ?3
                           and b.bookingStatus IN (:status)
            )
            """)
    Optional<Car> findAvailableCarById(
            int id,
            LocalDateTime pickUpDate,
            LocalDateTime dropDownDate,
            List<String> status
    );


}



