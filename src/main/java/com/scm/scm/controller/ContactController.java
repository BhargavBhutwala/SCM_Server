package com.scm.scm.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm.entities.Contact;
import com.scm.scm.entities.SocialLink;
import com.scm.scm.entities.User;
import com.scm.scm.forms.ContactForm;
import com.scm.scm.helper.EmailHelper;
import com.scm.scm.services.ContactService;
import com.scm.scm.services.UserService;

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
   public String saveContact(@ModelAttribute ContactForm contactForm, Authentication authentication) {

      System.out.println(contactForm);

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

      return "redirect:/user/contacts/add";
   }
}
