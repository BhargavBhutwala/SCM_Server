package com.scm.scm.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.scm.entities.User;
import com.scm.scm.helper.ResourceNotFoundException;
import com.scm.scm.repositories.UserRepository;
import com.scm.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
   private UserRepository userRepository;

   private Logger logger = LoggerFactory.getLogger(this.getClass());

   @Override
   public User saveUser(User user) {

      String userId = UUID.randomUUID().toString();
      user.setUserId(userId);
      return userRepository.save(user);
   }

   @Override
   public Optional<User> getUserById(String id) {

      return userRepository.findById(id);
   }

   @Override
   public User updateUser(User user) {

      User user2 = userRepository.findById(user.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

      user2.setName(user.getName());
      user2.setEmail(user.getEmail());
      user2.setPassword(user.getPassword());
      user2.setPhoneNumber(user.getPhoneNumber());
      user2.setAbout(user.getAbout());
      user2.setProfilePic(user.getProfilePic());
      user2.setEnabled(user.isEnabled());
      user2.setEmailVerified(user.isEmailVerified());
      user2.setPhoneNumberVerified(user.isPhoneNumberVerified());
      user2.setProvider(user.getProvider());
      user2.setProviderId(user.getProviderId());

      // save the user
      return userRepository.save(user2);
   }

   @Override
   public void deleteUser(String id) {

      userRepository.deleteById(id);
   }

   @Override
   public boolean isUserExist(String id) {

      User user = userRepository.findById(id).orElse(null);
      return user != null ? true : false;
   }

   @Override
   public boolean isUserExistByEmail(String email) {

      User user = userRepository.findByEmail(email).orElse(null);
      return user != null ? true : false;
   }

   @Override
   public List<User> getAllUsers() {

      return userRepository.findAll();
   }

}
