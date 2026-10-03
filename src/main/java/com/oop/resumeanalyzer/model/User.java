package com.oop.resumeanalyzer.model;

import jakarta.persistence.*;

/**
 * User (Module 1)
 * ---------------
 * Demonstrates ENCAPSULATION: all fields are private and only reachable
 * through public getters/setters, protecting the internal state of the object.
 */
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String email;
    private String phone;
    private String education;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Resume resume;

    public User() {
    }

    public User(String name, String email, String phone, String education) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.education = education;
    }

    // ---- register() / updateProfile() from the design slide ----
    public void updateProfile(String name, String email, String phone, String education) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.education = education;
    }

    public String getDetails() {
        return String.format("%s | %s | %s | %s", name, email, phone, education);
    }

    // ---- Getters & Setters (Encapsulation) ----
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}
