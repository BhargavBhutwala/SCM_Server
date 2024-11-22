package com.scm.scm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

   // create user and then login using java code with in memory service
   @Bean
   public UserDetailsService userDetailsService() {

      // create user
      // UserDetails user =
      // User.withDefaultPasswordEncoder().username("admin").password("password").build();

      InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
      detailsManager.createUser(User.withDefaultPasswordEncoder().username("admin").password("password").build());
      return detailsManager;
   }
}
