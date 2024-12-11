package com.scm.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

   // add contacts page
   @RequestMapping("/add")
   public String addContactsView() {
      return "user/add_contact";
   }
}
