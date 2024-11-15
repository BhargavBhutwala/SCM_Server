package com.scm.scm.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  private String userId;

  @Column(name = "user_name", nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  private String password;

  private String phoneNumber;

  @Column(length = 2400)
  private String about;

  @Column(length = 2400)
  private String profilePic;

  // information
  private boolean enabled = false;

  private boolean emailVerified = false;

  private boolean phoneNumberVerified = false;

  // signup information (self, google, github)
  private Providers provider = Providers.SELF;

  private String providerId;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Contact> contacts = new ArrayList<Contact>();

}
