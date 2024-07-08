package com.squad.couponExchangePlatform.service;

import com.squad.couponExchangePlatform.model.Person;
import com.squad.couponExchangePlatform.model.Roles;
import com.squad.couponExchangePlatform.repos.PersonRepository;
import com.squad.couponExchangePlatform.repos.RolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewUser(Person person){
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName("USER");
//        log.info("----------role: -------------------\n"+role.toString());
        person.setRoles(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        if(person!=null && person.getPersonId()>0){
            isSaved = true;
        }
        return isSaved;
    }
}
