package com.scm.scm.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class User implements UserDetails {

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

  private String emailToken;

  // signup information (self, google, github)
  @Enumerated(value = EnumType.STRING)
  private Providers provider = Providers.SELF;

  private String providerId;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Contact> contacts = new ArrayList<Contact>();

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> roleList = new ArrayList<>();

  // spring security methods [UserDetails]

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    // list of roles[ADMIN, USER] -> Collections of
    // SimpleGrantedAuthority[roles{ADMIN, USER}]

    Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role))
        .collect(Collectors.toList());

    return roles;
  }

  // for this project our email address is our username
  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }

}
