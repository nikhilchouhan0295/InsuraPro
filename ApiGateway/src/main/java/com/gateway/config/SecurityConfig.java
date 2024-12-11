package com.gateway.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.gateway.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
            .authorizeExchange(exchanges -> exchanges
           //     .pathMatchers("/public/**").permitAll()   // Public routes, no auth required
                .pathMatchers("/userservice/**", "/policyservice/test/**","/paymentservice/test").permitAll() // Allow access without authentication
                .pathMatchers(HttpMethod.GET,"/POLICYSERVICE/test").permitAll() 
                .pathMatchers("/policyservice/addPolicy","/policyservice/updatePolicy","/policyservice/deletePolicy"
                		).permitAll()
                .pathMatchers("/policyservice/showAllPolicies/**","/policyservice/buyPolicy/**","/policyservice/amount/**",
                		"/policyservice/expiryDate/**","/policyservice/getDetails/**","/policyservice/notification/**").hasAnyRole("ROLE_admin","ROLE_user")
                .pathMatchers(HttpMethod.GET,"/paymentservice/makePayment/**").hasRole("ROLE_user")
                .pathMatchers(HttpMethod.POST,"/paymentservice/showPayments").hasRole("ROLE_admin")
                .pathMatchers(HttpMethod.GET,"/notificationservice/notication/sendMail/**").hasRole("ROLE_admin")
                .pathMatchers("/claimservice/claim/**").hasRole("ROLE_user")
                .pathMatchers("/claimservice/showAllClaim").hasRole("ROLE_admin")
                .anyExchange().authenticated()              // All other routes require authentication
            )
            .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION) // Add JWT filter
            .csrf().disable()  // Disable CSRF for stateless APIs
            .build();
        
        
    }
    
    
}

	
