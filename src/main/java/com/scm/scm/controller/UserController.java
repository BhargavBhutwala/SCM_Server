package com.scm.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

   // user dashboard
   @GetMapping("/dashboard")
   public String getDashboard() {
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
