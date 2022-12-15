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

    public UserInformation(Long id, String email, String firstName, String lastName, String postCode, Boolean isActive) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
        this.isActive = isActive;
    }

    public UserInformation(String email, String firstName, String lastName, String postCode, Boolean isActive) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostCode() {
        return postCode;
    }

    public Boolean getActive() {
        return isActive;
    }
}
