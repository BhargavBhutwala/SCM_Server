package com.scm.scm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.scm.entities.Contact;
import com.scm.scm.entities.User;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

   // custom finder method
   List<Contact> findByUser(User user);
}
