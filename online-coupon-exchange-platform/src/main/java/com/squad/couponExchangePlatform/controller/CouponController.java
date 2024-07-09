package com.squad.couponExchangePlatform.controller;

import com.squad.couponExchangePlatform.model.Brands;
import com.squad.couponExchangePlatform.model.Coupon;
import com.squad.couponExchangePlatform.model.Person;
import com.squad.couponExchangePlatform.repos.BrandsRepository;
import com.squad.couponExchangePlatform.repos.CouponRepository;
import com.squad.couponExchangePlatform.repos.PersonRepository;
import com.squad.couponExchangePlatform.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class CouponController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private BrandsRepository brandsRepository;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/displayCoupons")
    public String displayCoupons(@RequestParam("brandId") int brandId, Model model) {
        List<Coupon> allCoupons = couponService.getCouponsByBrandId(brandId);
        List<Coupon> unusedCoupons = allCoupons.stream()
                .filter(coupon -> coupon.getUsedId() == null)
                .collect(Collectors.toList());

        Optional<Brands> brand = brandsRepository.findById(brandId);
        if (brand.isPresent()) {
            model.addAttribute("coupons", unusedCoupons);
            model.addAttribute("brand", brand.get());
            return "couponsBrand";
        } else {
            model.addAttribute("errorMessage", "Brand not found");
            return "couponsBrand";
        }
    }

    @PostMapping("/submitUsedCoupon")
    public String submitUsedCoupon(@RequestParam("couponId") int couponId,Authentication authentication, Model model) {
        try {
            Person loggedInPerson = personRepository.readByEmail(authentication.getName());

            if (loggedInPerson != null) {
                Optional<Coupon> couponOpt = couponRepository.findById(couponId);

                if (couponOpt.isPresent()) {
                    Coupon coupon = couponOpt.get();
                    coupon.setUsedId(loggedInPerson);
                    couponRepository.save(coupon);
                    model.addAttribute("successMessage", "Coupon used successfully!");
                    String shopNow = coupon.getShopNow();
                    if(shopNow==null || shopNow.isEmpty()){
                        return "redirect:/displayCoupons?brandId=" + coupon.getBrands().getBrandId();
                    }
                    return "redirect:"+shopNow;
                } else {
                    model.addAttribute("errorMessage", "Coupon not found");
                }
            } else {
                model.addAttribute("errorMessage", "User not found");
            }
        } catch (Exception e) {
            log.error("Error using coupon", e);
            model.addAttribute("errorMessage", "An error occurred while using the coupon");
        }

        return "redirect:/displayCoupons";
    }

    @PostMapping("/submitWishlistCoupon")
    public String submitWishlistCoupon(@RequestParam("couponId") int couponId, Authentication authentication, Model model) {
        log.info("-------entered-----------");
        try {
            Person loggedInPerson = personRepository.readByEmail(authentication.getName());

            if (loggedInPerson != null) {
                Optional<Coupon> couponOpt = couponRepository.findById(couponId);

                if (couponOpt.isPresent()) {
                    Coupon coupon = couponOpt.get();
                    coupon.setWishlistId(loggedInPerson);
                    loggedInPerson.getCoupons().add(coupon);
                    personRepository.save(loggedInPerson);
                    model.addAttribute("successMessage", "Coupon added to wishlist successfully!");
                    return "redirect:/displayCoupons?brandId=" + coupon.getBrands().getBrandId();
                } else {
                    model.addAttribute("errorMessage", "Coupon not found");
                }
            } else {
                model.addAttribute("errorMessage", "User not found");
            }
        } catch (Exception e) {
            log.error("Error adding coupon to wishlist", e);
            model.addAttribute("errorMessage", "An error occurred while adding the coupon to wishlist");
        }

        return "redirect:/displayCoupons";
    }
}


