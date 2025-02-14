package com.scm.scm.forms;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.scm.scm.entities.SocialLink;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

   @NotBlank(message = "Name is required")
   private String name;

   @Email(message = "Invalid email address")
   @NotBlank(message = "Email address is required")
   private String email;

   @NotBlank(message = "Phone number is required")
   @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
   private String phoneNumber;

   private String address;

   // will create a custom validator using annotations
   // and validate size and resolution
   private MultipartFile picture;

   private String contactImage;

   private String description;

   private boolean favorite;

   private List<SocialLink> socialLinks;
}
