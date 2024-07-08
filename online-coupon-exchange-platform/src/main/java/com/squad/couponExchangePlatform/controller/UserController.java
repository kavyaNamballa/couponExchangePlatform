package com.squad.couponExchangePlatform.controller;


import com.squad.couponExchangePlatform.model.Coupon;
import com.squad.couponExchangePlatform.model.Person;
import com.squad.couponExchangePlatform.service.CouponService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
//@RequestMapping("user")
public class UserController {

    @Autowired
    CouponService couponService;

//    @RequestMapping("/displayPostedCoupons")
    public String displayPostedCoupons(Model model, HttpSession session){
        log.info("-----------------------------------------");
        Person loggedInPerson = (Person) session.getAttribute("loggedInPerson");
        if (loggedInPerson != null) {
            log.info("-----------------------------------------");
            List<Coupon> postedCoupons = couponService.getCouponsByPersonId(loggedInPerson.getPersonId());
            model.addAttribute("coupons", postedCoupons);
        }else{
            log.info("------------empty-----------------------------");
        }
        return "coupons.html";
    }
}
