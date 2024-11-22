package com.scm.scm.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.scm.repositories.UserRepository;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {

   @Autowired
   private UserRepository userRepository;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      // load the user from database

      return this.userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
   }

}
