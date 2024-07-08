package com.squad.couponExchangePlatform.controller;

import com.squad.couponExchangePlatform.model.Contact;
import com.squad.couponExchangePlatform.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {

    private ContactService contactService;

    @Autowired
    ContactController(ContactService contactService){
        this.contactService = contactService;
    }
    @RequestMapping("/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact",new Contact());
        return "contact.html";
    }
}
