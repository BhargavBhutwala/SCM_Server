package com.scm.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class UserForm {

   @NotBlank(message = "Username is required")
   @Size(min = 3, message = "Minimum 3 characters required")
   private String name;

   @Email(message = "Invalid Email")
   @NotBlank(message = "Email is required")
   private String email;

   @NotBlank(message = "Password is required")
   @Size(min = 8, message = "Minimum 8 characters required")
   private String password;

   @Size(min = 10, max = 12, message = "Invalid phone number")
   private String phoneNumber;

   @NotBlank(message = "About is required")
   private String about;
}
