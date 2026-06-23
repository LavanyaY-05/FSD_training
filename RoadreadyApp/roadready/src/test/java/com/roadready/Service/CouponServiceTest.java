package com.roadready.Service;

import com.roadready.dto.CouponReqDto;
import com.roadready.exceptions.ResourceNotFoundException;
import com.roadready.model.Coupon;
import com.roadready.repository.CouponRepository;
import com.roadready.service.CouponService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {
    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private CouponService couponService;

    private Coupon coupon;

    @BeforeEach
    public void sampleDate() {
        coupon = new Coupon();
        coupon.setId(1000);
        coupon.setCouponCode("ROADREADY50");
        coupon.setDiscountValue(new java.math.BigDecimal("50.00"));
        coupon.setExpiryDate(java.time.LocalDate.now().plusMonths(3));
        coupon.setActive(true);
        coupon.setMaxUsage(500);
        coupon.setUsedCount(0);

    }

    @Test
    public void getCoupon_MustReturnEmpty() {
        when(couponRepository.findById(1000)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> couponService.getById(1000))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Invalid Coupon ID");
        verify(couponRepository, times(1)).findById(1000);
    }

    @Test
    public void getCoupon_ReturnCoupon() {
        when(couponRepository.findById(1000)).thenReturn(Optional.of(coupon));
        Coupon actualCall = couponService.getById(1000);
        assertThat(actualCall.getId()).isEqualTo(coupon.getId());
        verify(couponRepository, times(1)).findById(1000);
    }


    @Test
    public void getCouponByCouponCode_MustReturnCoupon() {
        when(couponRepository.getCoupon("ROADREADY50")).thenReturn(coupon);
        Coupon actualCall = couponService.getCouponByCouponCode("ROADREADY50");
        assertThat(actualCall.getId()).isEqualTo(coupon.getId());
        verify(couponRepository, times(1)).getCoupon("ROADREADY50");
    }

    @Test
    public void getCouponByCouponCode_MustReturnNull() {
        when(couponRepository.getCoupon("ROADREADY50")).thenReturn(null);
        Coupon actualCall = couponService.getCouponByCouponCode("ROADREADY50");
        assertThat(actualCall).isEqualTo(null);
        verify(couponRepository, times(1)).getCoupon("ROADREADY50");
    }

    @Test
    public void addCoupon() {
        CouponReqDto dto = new CouponReqDto(
                "ROADREADY50",
                new java.math.BigDecimal("50.00"),
                java.time.LocalDate.now().plusMonths(3),
                500
        );
        when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);

        Coupon actualCoupon = couponService.addCoupon(dto);
        assertThat(actualCoupon.getId()).isEqualTo(coupon.getId());
        verify(couponRepository, times(1)).save(any(Coupon.class));
    }

    @Test
    public void deleteCoupon() {
        when(couponRepository.findById(1000)).thenReturn(Optional.of(coupon));
        couponService.deleteCoupon(1000);

        verify(couponRepository, times(1)).findById(1000);
        verify(couponRepository, times(1)).save(coupon);


    }


}
