package com.example.prescripto.dto.patient;

import com.example.prescripto.dto.BaseResponseDto;

public class DoctorDto {
    private String email;
    private String name;
    private Boolean available;
    private String experience;
    private Integer fees;
    private String about;
    private String speciality;
    private String education;
    private String addLine1;
    private String addLine2;
    private String profileImage;

    public DoctorDto() {}

    public DoctorDto(String email, String name, Boolean available, String experience, Integer fees, String about, String speciality, String education, String addLine1, String addLine2, String profileImage) {
        this.email = email;
        this.name = name;
        this.available = available;
        this.experience = experience;
        this.fees = fees;
        this.about = about;
        this.speciality = speciality;
        this.education = education;
        this.addLine1 = addLine1;
        this.addLine2 = addLine2;
        this.profileImage = profileImage;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
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
}
