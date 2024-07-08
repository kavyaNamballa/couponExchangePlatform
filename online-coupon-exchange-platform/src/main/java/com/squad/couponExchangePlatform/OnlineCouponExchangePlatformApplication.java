package com.squad.couponExchangePlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.squad.couponExchangePlatform.repos")
@EntityScan("com.squad.couponExchangePlatform.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class OnlineCouponExchangePlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineCouponExchangePlatformApplication.class, args);
	}
}
