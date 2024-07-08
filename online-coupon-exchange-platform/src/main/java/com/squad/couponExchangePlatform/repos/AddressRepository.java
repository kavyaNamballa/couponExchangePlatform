package com.squad.couponExchangePlatform.repos;

import com.squad.couponExchangePlatform.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Person,Integer> {

}
