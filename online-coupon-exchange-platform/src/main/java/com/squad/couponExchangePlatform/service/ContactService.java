package com.squad.couponExchangePlatform.service;

import com.squad.couponExchangePlatform.constants.Constants;
import com.squad.couponExchangePlatform.model.Contact;
import com.squad.couponExchangePlatform.repos.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j // this lombok annotation automatically generated log object based upon the class name
@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
//        log.info(contact.toString());
        contact.setStatus(Constants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if(savedContact != null && savedContact.getContactId()>0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findAllMsgs(){
        Iterable<Contact> list1 = contactRepository.findAll();
        List<Contact> list = new ArrayList<>();

        // Iterate over the iterable and add each element to the list
        for (Contact contact : list1) {
            list.add(contact);
        }
        return list;
    }

    public List<Contact> findOpenStatusMsgs(){
        List<Contact> list = contactRepository.findByStatus(Constants.OPEN);
        return list;
    }
    public boolean updateMsgStatus(int id){
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById(id);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(Constants.CLOSE);
        });
        Contact updatedContact = contactRepository.save(contact.get());
        if(null != updatedContact && updatedContact.getUpdatedBy()!=null) {
            isUpdated = true;
        }
        return isUpdated;
    }

}
