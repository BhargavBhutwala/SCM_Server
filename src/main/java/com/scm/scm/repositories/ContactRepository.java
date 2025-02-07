package com.scm.scm.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.scm.entities.Contact;
import com.scm.scm.entities.User;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

   // custom finder method
   Page<Contact> findByUser(User user, Pageable pageable);
}
