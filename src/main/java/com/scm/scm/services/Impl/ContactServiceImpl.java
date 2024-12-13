package com.scm.scm.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.scm.entities.Contact;
import com.scm.scm.entities.User;
import com.scm.scm.helper.ResourceNotFoundException;
import com.scm.scm.repositories.ContactRepository;
import com.scm.scm.services.ContactService;
import com.scm.scm.services.UserService;

@Service
public class ContactServiceImpl implements ContactService {

   @Autowired
   private ContactRepository contactRepository;

   @Autowired
   private UserService userService;

   @Override
   public Contact saveContact(Contact contact) {

      String contactId = UUID.randomUUID().toString();
      contact.setId(contactId);

      return contactRepository.save(contact);
   }

   @Override
   public Optional<Contact> getContactById(String id) {

      return contactRepository.findById(id);
   }

   @Override
   public Contact updateContact(Contact contact) {

      Contact contact2 = contactRepository.findById(contact.getId()).orElse(null);

      if (contact2 != null) {
         contact2.setName(contact.getName());
         contact.setEmail(contact.getEmail());
         contact2.setPhoneNumber(contact.getPhoneNumber());
         contact2.setAddress(contact.getAddress());
         contact2.setFavorite(contact.isFavorite());
         contact2.setDescription(contact.getDescription());
         contact2.setPicture(contact.getPicture());
         contact2.setSocialLinks(contact.getSocialLinks());

         return contactRepository.save(contact2);
      } else
         return contactRepository.save(contact);
   }

   @Override
   public void deleteContact(String id) {

      contactRepository.deleteById(id);
   }

   @Override
   public List<Contact> getAllContacts() {

      return contactRepository.findAll();
   }

   @Override
   public List<Contact> getContactsByUserId(String userId) {

      User user = userService.getUserById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("No user with id " + userId));

      return contactRepository.findByUser(user);
   }

}
