package com.scm.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.scm.entities.User;
import com.scm.scm.helper.EmailHelper;
import com.scm.scm.services.UserService;

@ControllerAdvice
public class RootController {

   @Autowired
   private UserService userService;

   @ModelAttribute
   public void setLoggedInUserInformation(Model model, Authentication authentication) {

      if (authentication == null) {
         return;
      }

      System.out.println("Adding logged in user information to the model");

      String email = EmailHelper.getEmailOfLoggedInUser(authentication);

      System.out.println(email);

      User user = userService.getUserByEmail(email);

      System.out.println(user.getName());

      model.addAttribute("loggedInUser", user);

   }

}
