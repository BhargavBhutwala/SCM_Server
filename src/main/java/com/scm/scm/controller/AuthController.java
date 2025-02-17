package com.scm.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.scm.entities.User;
import com.scm.scm.helper.Message;
import com.scm.scm.helper.MessageType;
import com.scm.scm.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

   @Autowired
   private UserService userService;

   // verify email
   @RequestMapping("/verify-email")
   public String verifyEmail(@RequestParam("token") String token, HttpSession session) {

      User user = userService.getUserByEmailToken(token);

      if (user != null) {
         user.setEmailVerified(true);
         user.setEnabled(true);
         userService.updateUser(user);

         Message message = new Message();
         message.setContent("Account verified!");
         message.setType(MessageType.green);

         session.setAttribute("alert", message);

         return "redirect:/login";
      }

      return "error_page";

   }

}
