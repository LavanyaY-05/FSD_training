package com.roadready.repository;

import com.roadready.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {


    Optional<Coupon> findByCouponCode(String couponCode);

    @Query("""
            select c from Coupon c where c.couponCode = ?1""")
    Coupon getCoupon(String couponCode);
}
