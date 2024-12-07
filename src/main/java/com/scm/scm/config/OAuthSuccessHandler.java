package com.scm.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.scm.entities.Providers;
import com.scm.scm.entities.User;
import com.scm.scm.helper.AppConstants;
import com.scm.scm.repositories.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

   Logger logger = LoggerFactory.getLogger(OAuthSuccessHandler.class);

   @Autowired
   private UserRepository userRepository;

   @Autowired
   @Lazy
   private PasswordEncoder passwordEncoder;

   @SuppressWarnings("null")
   @Override
   public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
         Authentication authentication) throws IOException, ServletException {

      // identify provider

      OAuth2AuthenticationToken oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

      String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

      // System.out.println(authorizedClientRegistrationId);

      DefaultOAuth2User oauth2User = (DefaultOAuth2User) authentication.getPrincipal();

      // oauth2User.getAttributes().forEach((key, value) -> {
      // logger.info("{} -> {}", key, value);
      // });

      User user = new User();
      user.setUserId(UUID.randomUUID().toString());
      user.setPassword(passwordEncoder.encode("password"));
      user.setEnabled(true);
      user.setEmailVerified(true);
      user.setRoleList(List.of(AppConstants.ROLE_USER));

      if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {
         // google login
         user.setEmail(oauth2User.getAttribute("email").toString());
         user.setName(oauth2User.getAttribute("name").toString());
         user.setProfilePic(oauth2User.getAttribute("picture").toString());
         user.setProvider(Providers.GOOGLE);
         user.setProviderId(oauth2User.getName());

      } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {
         // github login
         String name = oauth2User.getAttribute("name").toString() != null ? oauth2User.getAttribute("name").toString()
               : oauth2User.getAttribute("login").toString();

         String email = oauth2User.getAttribute("email").toString() != null
               ? oauth2User.getAttribute("email").toString()
               : oauth2User.getAttribute("login").toString() + "@gmail.com";

         String profilePic = oauth2User.getAttribute("avatar_url").toString();

         user.setEmail(email);
         user.setName(name);
         user.setProfilePic(profilePic);
         user.setProvider(Providers.GITHUB);
         user.setProviderId(oauth2User.getName());

      } else {
         logger.info("Unknown provider");
      }

      // save user data to database

      // check if user is already present in database
      // if not, save the user
      User user3 = userRepository.findByEmail(user.getEmail()).orElse(null);

      if (user3 == null) {
         userRepository.save(user);
      }

      new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
   }

}
