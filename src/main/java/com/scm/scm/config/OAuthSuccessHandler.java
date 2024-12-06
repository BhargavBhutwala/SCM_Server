package com.scm.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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

   // Logger logger = LoggerFactory.getLogger(OAuthSuccessHandler.class);

   @Autowired
   private UserRepository userRepository;

   @Autowired
   @Lazy
   private PasswordEncoder passwordEncoder;

   @SuppressWarnings("null")
   @Override
   public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
         Authentication authentication) throws IOException, ServletException {

      // get user data from request and store it in database before redirecting

      DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

      // logger.info(user.getName());

      // user.getAttributes().forEach((key, value) -> {
      // logger.info("{}: {}", key, value);
      // });

      String email = user.getAttribute("email").toString();
      String name = user.getAttribute("name").toString();
      String profilePic = user.getAttribute("picture").toString();

      // save user data to database

      User user2 = new User();
      user2.setUserId(UUID.randomUUID().toString());
      user2.setName(name);
      user2.setEmail(email);
      user2.setProfilePic(profilePic);
      user2.setPassword(passwordEncoder.encode("password"));
      user2.setProvider(Providers.GOOGLE);
      user2.setEnabled(true);
      user2.setEmailVerified(true);
      user2.setProviderId(user.getName());
      user2.setRoleList(List.of(AppConstants.ROLE_USER));

      // check if user is already present in database
      // if not, save the user
      User user3 = userRepository.findByEmail(email).orElse(null);

      if (user3 == null) {
         userRepository.save(user2);
      }

      new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
   }

}
