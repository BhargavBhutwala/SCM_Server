package com.scm.scm.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

public class EmailHelper {

   @SuppressWarnings("null")
   public static String getEmailOfLoggedInUser(Authentication authentication) {

      if (authentication instanceof OAuth2AuthenticationToken) {

         OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

         String clientId = token.getAuthorizedClientRegistrationId();

         DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

         String email = "";

         if (clientId.equalsIgnoreCase("google")) {

            System.out.println("Getting email from Google");

            email = user.getAttribute("email").toString();

         } else if (clientId.equalsIgnoreCase("github")) {

            System.out.println("Getting email from Github");

            email = user.getAttribute("email").toString();

         }
         return email;
      } else {
         System.out.println("Getting email from database");
         return authentication.getName(); // if not using OAuth, return username
      }
   }
}
