package com.squad.couponExchangePlatform.repos;

import com.squad.couponExchangePlatform.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Integer> {
    Roles getByRoleName(String role);
}
