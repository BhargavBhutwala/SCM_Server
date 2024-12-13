package com.scm.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.scm.entities.Contact;

public interface ContactService {

   public Contact saveContact(Contact contact);

   public Optional<Contact> getContactById(String id);

   public Contact updateContact(Contact contact);

   public void deleteContact(String id);

   public List<Contact> getAllContacts();

   public List<Contact> getContactsByUserId(String userId);

}
