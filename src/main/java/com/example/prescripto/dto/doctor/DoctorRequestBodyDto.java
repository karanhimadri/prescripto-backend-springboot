package com.example.prescripto.dto.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctorRequestBodyDto {

    private String email;
    private String name;
    private String password;
    private String role;
    private Boolean available;
    private String experience;
    private Integer fees;
    private String about;
    private String speciality;
    private String education;

    @JsonProperty("addLine1")
    private String addLine1;

    @JsonProperty("addLine2")
    private String addLine2;

    private String profileImage;

    public DoctorRequestBodyDto() {};

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Boolean getAvailable() {
        return available;
    }

    public String getExperience() {
        return experience;
    }

    public Integer getFees() {
        return fees;
    }

    public String getAbout() {
        return about;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getEducation() {
        return education;
    }

    public String getAddLine1() {
        return addLine1;
    }

    public String getAddLine2() {
        return addLine2;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setFees(Integer fees) {
        this.fees = fees;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setAddLine1(String addLine1) {
        this.addLine1 = addLine1;
    }

    public void setAddLine2(String addLine2) {
        this.addLine2 = addLine2;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
