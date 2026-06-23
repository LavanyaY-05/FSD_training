package com.roadready.service;

import com.roadready.Mapper.CouponMapper;
import com.roadready.dto.CouponReqDto;
import com.roadready.dto.CouponRespDto;
import com.roadready.dto.ValidateCouponRespDto;
import com.roadready.exceptions.CouponException;
import com.roadready.exceptions.ResourceNotFoundException;
import com.roadready.model.Coupon;
import com.roadready.repository.CouponRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;


    public Coupon getById(int id) {
        return couponRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Coupon ID"));

    }

    public Coupon getCouponByCouponCode(String couponCode) {

        return couponRepository.getCoupon(couponCode);


    }

    public List<CouponRespDto> getAllCoupon() {

        List<Coupon> coupons = couponRepository.findAll();
        return coupons.stream()
                .map(couponMapper::mapCouponEntityToDto)
                .toList();
    }

    public CouponRespDto getCoupon(int id) {
        Coupon coupon = getById(id);
        return couponMapper.mapCouponEntityToDto(coupon);
    }

    public Coupon addCoupon(@Valid CouponReqDto dto) {
        Coupon coupon = new Coupon();
        coupon.setCouponCode(dto.couponCode());
        coupon.setDiscountValue(dto.discountValue());
        coupon.setExpiryDate(dto.expiryDate());
        coupon.setMaxUsage(dto.maxUsage());
        return couponRepository.save(coupon);

    }

    public void updateCoupon(int id, @Valid CouponReqDto dto) {
        Coupon coupon = getById(id);
        addCoupon(dto);
    }

    public ValidateCouponRespDto validateCoupon(String couponCode) {

        Coupon coupon = getCouponByCouponCode(couponCode);
        if (coupon == null)
            throw new CouponException("Invalid Coupon");


        if (!coupon.isActive())
            throw new CouponException("Validation failed : the coupon is not active");

        if (coupon.getExpiryDate().isBefore(LocalDate.now()))
            throw new CouponException("Validation failed : the coupon has expired ");

        return new ValidateCouponRespDto(
                coupon.getCouponCode(),
                "Success: Coupon Code Applied",
                coupon.getDiscountValue()
        );
    }

    public void deleteCoupon(int id) {
        Coupon coupon = getById(id);
        coupon.setActive(false);
        couponRepository.save(coupon);
    }
}
