package com.scm.scm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm.helper.EmailHelper;

@Controller
@RequestMapping("/user")
public class UserController {

   // user dashboard
   @GetMapping("/dashboard")
   public String getDashboard(Authentication authentication) {
      String email = EmailHelper.getEmailOfLoggedInUser(authentication);
      System.out.println("Email: " + email);
      return "user/dashboard";
   }

   // user profile
   @GetMapping("/profile")
   public String getProfile() {
      return "user/profile";
   }

   // user add contacts

   // user view contacts

   // user edit contact

   // user delete contact
}
