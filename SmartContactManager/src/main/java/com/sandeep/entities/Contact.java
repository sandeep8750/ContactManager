package com.sandeep.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Changed from String to Long for MySQL compatibility

    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String pricture; // Typo in the field name?

    @Column(length = 1000)
    private String description;

    private boolean favorite = false;
    private String websiteLink;
    private String linkedInLink;
    
    @ManyToOne
    private User user;
    
    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SocialLinks> socialLinks = new ArrayList<>();
   
}
