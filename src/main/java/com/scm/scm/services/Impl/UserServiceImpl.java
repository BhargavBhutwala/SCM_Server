package com.scm.scm.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.scm.entities.User;
import com.scm.scm.helper.AppConstants;
import com.scm.scm.helper.EmailHelper;
import com.scm.scm.helper.ResourceNotFoundException;
import com.scm.scm.repositories.UserRepository;
import com.scm.scm.services.EmailService;
import com.scm.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Autowired
   private EmailService emailService;

   // private Logger logger = LoggerFactory.getLogger(this.getClass());

   @Override
   public User saveUser(User user) {

      // set userId
      String userId = UUID.randomUUID().toString();
      user.setUserId(userId);

      // encode password
      user.setPassword(passwordEncoder.encode(user.getPassword()));

      // set role
      user.setRoleList(List.of(AppConstants.ROLE_USER));

      // set email verification token
      String token = UUID.randomUUID().toString();

      user.setEmailToken(token);

      User savedUser = userRepository.save(user);

      // send email verification link
      String link = EmailHelper.getEmailVerificationLink(token);

      emailService.sendEmail(savedUser.getEmail(), "Account Verification: Smart Contact Manager", link);

      return savedUser;

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
      user2.setEmailToken(user.getEmailToken());

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

   @Override
   public User getUserByEmail(String email) {
      return userRepository.findByEmail(email).orElse(null);
   }

   @Override
   public User getUserByEmailToken(String token) {

      return userRepository.findByEmailToken(token);
   }

}
