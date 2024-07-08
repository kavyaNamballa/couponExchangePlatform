package com.squad.couponExchangePlatform.controller;

import com.squad.couponExchangePlatform.model.Address;
import com.squad.couponExchangePlatform.model.Person;
import com.squad.couponExchangePlatform.model.Profile;
import com.squad.couponExchangePlatform.repos.PersonRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ProfileController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/displayProfile")
    public String displayProfile(Model model, Authentication authentication) {
        String email = authentication.getName();
        log.info("Fetching person with email: {}", email);
        Person person = personRepository.readByEmail(email);
        if (person == null) {
            log.warn("No person found with email: {}", email);
            return "home.html";
        }
        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setMobileNumber(person.getMobileNumber());
        profile.setEmail(person.getEmail());
        if (person.getAddress() != null && person.getAddress().getAddressId() > 0) {
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode());
        }
        model.addAttribute("profile", profile);
        return "profile.html";
    }

    @PostMapping(value = "/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors,
                                Authentication authentication) {
        if (errors.hasErrors()) {
            return "profile";
        }
        Person person = personRepository.readByEmail(authentication.getName());
        person.setName(profile.getName());
        person.setEmail(profile.getEmail());
        person.setMobileNumber(profile.getMobileNumber());
        if (person.getAddress() == null || !(person.getAddress().getAddressId() > 0)) {
            person.setAddress(new Address());
        }
        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());
        personRepository.save(person);
        return "redirect:/displayProfile";
    }
}
