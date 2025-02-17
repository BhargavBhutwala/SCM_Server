package com.scm.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.scm.entities.User;

public interface UserService {

   public User saveUser(User user);

   public Optional<User> getUserById(String id);

   public User updateUser(User user);

   public void deleteUser(String id);

   public boolean isUserExist(String id);

   public boolean isUserExistByEmail(String email);

   public List<User> getAllUsers();

   public User getUserByEmail(String email);

   public User getUserByEmailToken(String token);
}
