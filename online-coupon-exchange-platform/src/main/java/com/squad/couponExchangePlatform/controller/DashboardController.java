package com.squad.couponExchangePlatform.controller;

import com.squad.couponExchangePlatform.model.Coupon;
import com.squad.couponExchangePlatform.model.Person;
import com.squad.couponExchangePlatform.repos.CouponRepository;
import com.squad.couponExchangePlatform.repos.PersonRepository;
import com.squad.couponExchangePlatform.service.CouponService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CouponService couponService;

    @Autowired
    CouponRepository couponRepository;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session) {
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        session.setAttribute("loggedInPerson",person);
        return "dashboard";
    }

    @RequestMapping("/displayPostedCoupons")
    public String displayPostedCoupons(Model model, HttpSession session) {
        Person loggedInPerson = (Person) session.getAttribute("loggedInPerson");
        if (loggedInPerson != null) {
            List<Coupon> postedCoupons = couponService.getCouponsByPersonId(loggedInPerson.getPersonId());
//            for (Coupon coupon : postedCoupons) {
//                log.info("Coupon: " + coupon.getCode() + ", Brand: " + (coupon.getBrands() != null ? coupon.getBrands().getBrandName() : "No Brand"));
//            }
            model.addAttribute("coupons", postedCoupons);
            model.addAttribute("loggedInPersonId",loggedInPerson.getPersonId());
        } else {
            log.info("------------empty-----------------------------");
        }
        model.addAttribute("coupon", new Coupon());
        return "postedCoupons";
    }

    @PostMapping("/addCoupon")
    public String addCoupon(@ModelAttribute("coupon") Coupon coupon, Authentication authentication, Model model) {
        Person loggedInUser = personRepository.readByEmail(authentication.getName());
        coupon.setPersons(loggedInUser);
        couponRepository.save(coupon);
        model.addAttribute("successMessage", "Coupon added successfully!");
        return "redirect:/displayPostedCoupons";
    }

    @RequestMapping("/displayUsedCoupons")
    public String displayUsedCoupons(Model model,HttpSession session){
        Person loggedInPerson = (Person) session.getAttribute("loggedInPerson");
        if (loggedInPerson != null) {
            List<Coupon> usedCoupons = couponService.getCouponsByUsedId(loggedInPerson.getPersonId());
            model.addAttribute("coupons", usedCoupons);
        }else{
            log.info("------------empty---------------------------");
        }
        return "usedCoupons";
    }

    @RequestMapping("/displayWishlistCoupons")
    public String displayWishlistCoupons(Model model, HttpSession session, Authentication authentication) {
        Person loggedInPerson = personRepository.readByEmail(authentication.getName());
//        Person loggedInPerson = (Person) session.getAttribute("loggedInPerson");
        if (loggedInPerson != null) {
            Set<Coupon> wishlistCoupons = loggedInPerson.getCoupons();
            List<Coupon> unusedWishlistCoupons = wishlistCoupons.stream()
                    .filter(coupon -> coupon.getUsedId() == null)
                    .collect(Collectors.toList());
            model.addAttribute("coupons", unusedWishlistCoupons);
        } else {
            log.info("------------empty--------------------");
        }
        return "wishlistCoupons";
    }

    @PostMapping("/submitUsedCouponFromWishlist")
    public String submitUsedCouponFromWishlist(@RequestParam("couponId") int couponId, Authentication authentication, Model model,HttpSession session) {
        try {
            Person loggedInPerson = personRepository.readByEmail(authentication.getName());
//            Person loggedInPerson = (Person) session.getAttribute("loggedInPerson");
            if (loggedInPerson != null) {
                Optional<Coupon> couponOpt = couponRepository.findById(couponId);

                if (couponOpt.isPresent()) {
                    Coupon coupon = couponOpt.get();
                    coupon.setUsedId(loggedInPerson);
                    couponRepository.save(coupon);
                    model.addAttribute("successMessage", "Coupon used successfully!");
                    return "redirect:/displayWishlistCoupons";
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

        return "redirect:/displayWishlistCoupons";
    }

    @PostMapping("/submitWishlistCouponFromWishlist")
    public String submitWishlistCoupon(@RequestParam("couponId") int couponId, Authentication authentication, Model model) {
        log.info("-------entered-----------");
        try {
            Person loggedInPerson = personRepository.readByEmail(authentication.getName());

            if (loggedInPerson != null) {
                Optional<Coupon> couponOpt = couponRepository.findById(couponId);

                if (couponOpt.isPresent()) {
                    Coupon coupon = couponOpt.get();
                    if(coupon.getWishlistId() == null) {
                        coupon.setWishlistId(loggedInPerson);
                        loggedInPerson.getCoupons().add(coupon);
                    }else{
                        coupon.setWishlistId(null);
                        loggedInPerson.getCoupons().remove(coupon);
                    }
                    personRepository.save(loggedInPerson);
                    model.addAttribute("successMessage", "Coupon added to wishlist successfully!");
                    return "redirect:/displayWishlistCoupons";
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

        return "redirect:/displayWishlistCoupons";
    }


}
