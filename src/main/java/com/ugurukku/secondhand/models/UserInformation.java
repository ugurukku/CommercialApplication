package com.ugurukku.secondhand.models;

import javax.persistence.*;

@Entity
public class UserInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private String postCode;

    private Boolean isActive;

    public UserInformation() {
    }

    public UserInformation(String email, String firstName, String lastName, String postCode) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
    }
}
