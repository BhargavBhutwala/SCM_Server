package com.scm.scm.config;

import java.io.IOException;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.scm.scm.helper.Message;
import com.scm.scm.helper.MessageType;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {

   @Override
   public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
         AuthenticationException exception) throws IOException, ServletException {

      if (exception instanceof DisabledException) {
         // user is disabled

         Message message = new Message();
         message.setContent("Your account is disabled, verification link has been sent to your email");
         message.setType(MessageType.red);

         HttpSession session = request.getSession();
         session.setAttribute("alert", message);

         response.sendRedirect("/login");
      } else {
         response.sendRedirect("/login?error=true");
      }
   }

}
