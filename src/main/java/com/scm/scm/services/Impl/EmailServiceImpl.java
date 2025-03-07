package com.scm.scm.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.scm.scm.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

   @Autowired
   private JavaMailSender mailSender;

   @Override
   public void sendEmail(String recipient, String subject, String body) {

      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(recipient);
      message.setSubject(subject);
      message.setText(body);
      message.setFrom("verifyscm@demomailtrap.com");

      mailSender.send(message);
   }

}
