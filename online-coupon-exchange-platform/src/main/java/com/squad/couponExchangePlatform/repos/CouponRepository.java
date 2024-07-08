package com.squad.couponExchangePlatform.repos;

import com.squad.couponExchangePlatform.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    List<Coupon> findByBrandsBrandId(int brandId);
    List<Coupon> findByPersonsPersonId(int personId);
    List<Coupon> findByUsedIdPersonId(int personId);
//    List<Coupon> findByWishlistIdPersonId(int personId);
}
