package com.squad.couponExchangePlatform.service;

import com.squad.couponExchangePlatform.model.Coupon;
import com.squad.couponExchangePlatform.repos.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    public List<Coupon> getCouponsByBrandId(int brandId) {
        return couponRepository.findByBrandsBrandId(brandId);
    }

    public List<Coupon> getCouponsByPersonId(int personId){
        return couponRepository.findByPersonsPersonId(personId);
    }

    public List<Coupon> getCouponsByUsedId(int usedId){
        return couponRepository.findByUsedIdPersonId(usedId);
    }
}
