package com.scm.scm.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm.entities.Contact;
import com.scm.scm.entities.SocialLink;
import com.scm.scm.entities.User;
import com.scm.scm.forms.ContactForm;
import com.scm.scm.helper.EmailHelper;
import com.scm.scm.helper.Message;
import com.scm.scm.helper.MessageType;
import com.scm.scm.services.ContactService;
import com.scm.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

   @Autowired
   private ContactService contactService;

   @Autowired
   private UserService userService;

   // add contacts page
   @RequestMapping("/add")
   public String addContactsView(Model model) {

      ContactForm contactForm = new ContactForm();

      model.addAttribute("contactForm", contactForm);

      return "user/add_contact";
   }

   @PostMapping("/process-contact")
   public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult,
         Authentication authentication, HttpSession session) {

      System.out.println(contactForm);

      // validate contact form
      if (bindingResult.hasErrors()) {

         Message message = new Message();
         message.setContent("Please correct the following errors");
         message.setType(MessageType.red);

         session.setAttribute("alert", message);

         return "user/add_contact";
      }

      // save contact to database
      // contactForm --> contact

      Contact contact = new Contact();
      contact.setName(contactForm.getName());
      contact.setEmail(contactForm.getEmail());
      contact.setPhoneNumber(contactForm.getPhoneNumber());
      contact.setAddress(contactForm.getAddress());
      contact.setDescription(contactForm.getDescription());
      contact.setFavorite(contactForm.isFavorite());

      List<SocialLink> socialLinks = contactForm.getSocialLinks()
            .stream().map(link -> {
               SocialLink socialLink = new SocialLink();
               socialLink.setLink(link.getLink());
               socialLink.setContact(contact);
               return socialLink;
            }).collect(Collectors.toList());

      socialLinks.get(0).setTitle("Website");
      socialLinks.get(1).setTitle("LinkedIn");

      contact.setSocialLinks(socialLinks);

      // get user information and save it
      String email = EmailHelper.getEmailOfLoggedInUser(authentication);
      User user = userService.getUserByEmail(email);

      contact.setUser(user);

      // save contact
      contactService.saveContact(contact);

      // display contact saved message
      Message message = new Message();
      message.setContent("Contact saved successfully!");
      message.setType(MessageType.green);

      session.setAttribute("alert", message);

      return "redirect:/user/contacts/add";
   }
}
