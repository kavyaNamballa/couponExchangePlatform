package com.squad.couponExchangePlatform.repos;

import com.squad.couponExchangePlatform.model.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandsRepository extends JpaRepository<Brands, Integer> {
}
