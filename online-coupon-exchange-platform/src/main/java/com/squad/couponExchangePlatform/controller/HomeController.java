package com.squad.couponExchangePlatform.controller;

import com.squad.couponExchangePlatform.model.Brands;
import com.squad.couponExchangePlatform.repos.BrandsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Controller
public class HomeController {

    @RequestMapping(value={"", "/", "home"})
    public String home(){
        return "home.html";
    }
}
