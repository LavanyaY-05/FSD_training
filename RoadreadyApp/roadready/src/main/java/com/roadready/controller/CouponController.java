package com.roadready.controller;

import com.roadready.dto.CouponReqDto;
import com.roadready.dto.CouponRespDto;
import com.roadready.dto.ValidateCouponRespDto;
import com.roadready.service.CouponService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")

public class CouponController {

    private final CouponService couponService;

    @GetMapping("/getAll")
    public List<CouponRespDto> getAllCoupon() {
        return couponService.getAllCoupon();
    }

    @GetMapping("/get/{id}")
    public CouponRespDto getCoupon(@PathVariable int id) {
        return couponService.getCoupon(id);
    }

    @PostMapping("/add")
    public void addCoupon(@Valid @RequestBody CouponReqDto dto) {
        couponService.addCoupon(dto);
    }


    @PutMapping("/update/{id}")
    public void updateCoupon(@PathVariable int id, @Valid @RequestBody CouponReqDto dto) {
        couponService.updateCoupon(id, dto);
    }

    @GetMapping("/validate")
    public ValidateCouponRespDto validateCoupon(@RequestParam String couponCode) {
        return couponService.validateCoupon(couponCode);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCoupon(@PathVariable int id) {
        couponService.deleteCoupon(id);
    }

}
