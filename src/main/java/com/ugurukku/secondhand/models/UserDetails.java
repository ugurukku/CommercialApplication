package com.ugurukku.secondhand.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String phoneNumber;
    private  String address;
    private  String city;
    private  String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private  Users users;

    public UserDetails(Long id, String phoneNumber, String address, String city, String country, Users users) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.id = id;
        this.users = users;
    }

    public UserDetails() {

    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getPhoneNumber(), that.getPhoneNumber()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getCountry(), that.getCountry()) && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPhoneNumber(), getAddress(), getCity(), getCountry(), users);
    }
}
