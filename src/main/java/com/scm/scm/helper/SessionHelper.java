package com.scm.scm.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {

   @SuppressWarnings("null")
   public static void removeMessage() {
      // Remove the alert message from the session
      try {
         HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
               .getSession();
         session.removeAttribute("alert");
      } catch (Exception e) {
         System.out.println("Error removing alert message");
         e.printStackTrace();
      }
   }
}
