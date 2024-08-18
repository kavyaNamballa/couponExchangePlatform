package com.squad.couponExchangePlatform.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

        http.csrf((csrf) -> csrf
                        .ignoringRequestMatchers(mvcMatcherBuilder.pattern("/register/**"))
                        .ignoringRequestMatchers(mvcMatcherBuilder.pattern("/submitUsedCoupon"))
                        .ignoringRequestMatchers(mvcMatcherBuilder.pattern("/submitWishlistCoupon"))
                        .ignoringRequestMatchers(mvcMatcherBuilder.pattern("/submitUsedCouponFromWishlist"))
                        .ignoringRequestMatchers(mvcMatcherBuilder.pattern("/submitWishlistCouponFromWishlist"))
                        .ignoringRequestMatchers(mvcMatcherBuilder.pattern("/addCoupon"))
                        .ignoringRequestMatchers(mvcMatcherBuilder.pattern("/saveMsg"))
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(mvcMatcherBuilder.pattern("/dashboard")).hasAnyRole("USER", "ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/home")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/displayProfile")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/updateProfile")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/displayCoupons")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/displayPostedCoupons")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/displayUsedCoupons")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/displayWishlistCoupons")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/deleteCoupon")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/displayMessages")).hasRole("ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/closeMsg/**")).hasRole("ADMIN")
                        .requestMatchers(mvcMatcherBuilder.pattern("/about")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/login")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/logout")).permitAll()
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/assets/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/error")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/contact")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/saveMsg")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/submitUsedCoupon")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/submitUsedCouponFromWishlist")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/submitWishlistCouponFromWishlist")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/submitWishlistCoupon")).authenticated()
                        .requestMatchers(mvcMatcherBuilder.pattern("/addCoupon")).authenticated()
                )
                .formLogin(loginConfigurer -> loginConfigurer
                        .loginPage("/login")
                        .defaultSuccessUrl("/home")
                        .failureUrl("/login?error=true")
                        .successHandler(customAuthenticationSuccessHandler())
                        .permitAll())
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .permitAll())
                .httpBasic(Customizer.withDefaults());

        http.headers(headersConfigurer -> headersConfigurer
                .frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (request, response, authentication) -> response.sendRedirect("/home");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
