package com.example.prescripto.models.doctor;

import com.example.prescripto.models.junctionModel.Appointment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Doctor {
    @Id
    private String email;

    private String name;
    private String password;
    private String role;
    private Boolean available;
    private String experience;
    private Integer fees;

    @Column(columnDefinition = "TEXT") // ✅ allows >255 characters
    private String about;

    private String speciality;
    private String education;
    private String addLine1;
    private String addLine2;
    private String profileImage;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    public Doctor() {};

    public Doctor(String email, String name, String password, String role, String experience, Integer fees, String speciality, String about, String education, String addLine1, String addLine2, Boolean available) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.experience = experience;
        this.fees = fees;
        this.speciality = speciality;
        this.about = about;
        this.education = education;
        this.addLine1 = addLine1;
        this.addLine2 = addLine2;
        this.available = available;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
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

    public Boolean getAvailable() {
        return available;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
