package com.roadready.Mapper;

import com.roadready.dto.CouponRespDto;
import com.roadready.model.Coupon;
import org.springframework.stereotype.Component;

@Component
public class CouponMapper {

    public CouponRespDto mapCouponEntityToDto(Coupon coupon) {
        return new CouponRespDto(
                coupon.getId(),
                coupon.getCouponCode(),
                coupon.getDiscountValue(),
                coupon.getExpiryDate(),
                coupon.isActive(),
                coupon.getMaxUsage(),
                coupon.getUsedCount()
        );
    }
}
