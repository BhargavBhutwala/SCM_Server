package com.scm.scm.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contact {

   @Id
   private String id;

   private String name;

   private String email;

   private String phoneNumber;

   @Column(length = 2400)
   private String address;

   private String picture;

   @Column(length = 2400)
   private String description;

   private boolean favorite = false;

   @ManyToOne
   @JsonIgnore
   private User user;

   @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
   @JsonManagedReference
   private List<SocialLink> socialLinks = new ArrayList<>();

}
