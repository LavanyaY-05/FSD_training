package com.roadready.repository;

import com.roadready.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("""
                   select avg(r.rating)
                   from Review r
                   where r.booking.car.id = ?1
            """)
    Double getAvgRating(int carId);

    Page<Review> findByBookingCarId(int id, Pageable pageable);


    @Query("""
            select r from Review r
            order by r.rating
            desc
            """)
    List<Review> getCarReviewFeatured(Pageable pageable);

    Page<Review> findAllByBookingCustomerUserUsername(String name, Pageable pageable);
}
