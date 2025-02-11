package com.scm.scm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.scm.scm.entities.Contact;
import com.scm.scm.entities.User;

public interface ContactService {

   public Contact saveContact(Contact contact);

   public Optional<Contact> getContactById(String id);

   public Contact updateContact(Contact contact);

   public void deleteContact(String id);

   public List<Contact> getAllContacts();

   public Page<Contact> getContactsByUserId(String userId, int page, int size, String sortBy, String direction);

   public Page<Contact> getContactsByUser(User user, int page, int size, String sortBy, String direction);

   public Page<Contact> searchByName(User user, String name, int page, int size, String sortBy, String direction);

   public Page<Contact> searchByEmail(User user, String email, int page, int size, String sortBy, String direction);

   public Page<Contact> searchByPhoneNumber(User user, String phoneNumber, int page, int size, String sortBy,
         String direction);

}
