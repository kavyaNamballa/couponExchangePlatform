package com.squad.couponExchangePlatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int couponId;

//    @NotBlank(message = "Coupon code must not be blank")
    private String code;

    @NotBlank(message = "Coupon type must not be blank")
    private String couponType;

    @NotBlank(message = "Discount value must not be blank")
    private String discountValue;

//    @NotBlank(message = "Expiry date must not be blank")
    private LocalDate expiryDate;

    private int minPurchaseAmount;

    private String description;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Person.class)
    @JoinColumn(name = "person_id", referencedColumnName = "personId", nullable = false)
    private Person persons;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "brand_id", referencedColumnName = "brandId", nullable = true)
    private Brands brands;

    private String shopNow;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Person.class)
    @JoinColumn(name = "used_id", referencedColumnName = "personId", nullable = true)
    private Person usedId;

    @ManyToMany(mappedBy = "coupons", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Person> person = new HashSet<>();

    @Override
    public int hashCode() {
        return Integer.hashCode(couponId);
    }
}

