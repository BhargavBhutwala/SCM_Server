package com.scm.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.scm.services.Impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

   // create user and then login using java code with in memory service

   // @Bean
   // public UserDetailsService userDetailsService() {

   // create user
   // UserDetails user =
   // User.withDefaultPasswordEncoder().username("admin").password("password").build();

   // InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
   // detailsManager.createUser(User.withDefaultPasswordEncoder().username("admin").password("password").build());
   // return detailsManager;
   // }

   // or you can use database for user management

   @Autowired
   private SecurityCustomUserDetailService userDetailsService;

   @Bean
   public AuthenticationProvider authenticationProvider() {

      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

      // set user details service
      authenticationProvider.setUserDetailsService(this.userDetailsService);

      // set password encoder
      authenticationProvider.setPasswordEncoder(passwordEncoder());

      return authenticationProvider;
   }

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

      // configure public and private urls
      httpSecurity
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(
                  authorize -> {
                     // authorize.requestMatchers("/about", "/register", "/login",
                     // "/home").permitAll().anyRequest()
                     // .authenticated();
                     authorize.requestMatchers("/user/**").authenticated();
                     authorize.anyRequest().permitAll();
                  })
            .formLogin(formLogin -> {
               formLogin.loginPage("/login");
               formLogin.loginProcessingUrl("/authenticate");
               formLogin.defaultSuccessUrl("/user/dashboard");
               formLogin.failureForwardUrl("/login?error=true");
               formLogin.usernameParameter("email");
               formLogin.passwordParameter("password");
            });

      return httpSecurity.build();
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
}
