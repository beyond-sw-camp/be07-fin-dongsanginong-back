package org.samtuap.inong.domain.coupon.api;

import org.samtuap.inong.domain.coupon.dto.CouponCreateRequest;
import org.samtuap.inong.domain.coupon.entity.Coupon;
import org.samtuap.inong.domain.coupon.service.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupons")
public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/{id}/create")
    public ResponseEntity<?> createCoupon(@PathVariable("id") Long farmId,
                                          @RequestBody CouponCreateRequest request) {
        couponService.createCoupon(farmId, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
