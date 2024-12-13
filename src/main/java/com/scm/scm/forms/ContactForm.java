package com.scm.scm.forms;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.scm.scm.entities.SocialLink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ContactForm {

   private String name;

   private String email;

   private String phoneNumber;

   private String address;

   private MultipartFile picture;

   private String description;

   private boolean favorite;

   private List<SocialLink> socialLinks;
}
