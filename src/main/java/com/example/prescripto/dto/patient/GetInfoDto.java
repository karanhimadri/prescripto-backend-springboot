package com.example.prescripto.dto.patient;

import com.example.prescripto.dto.BaseResponseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetInfoDto implements BaseResponseDto {

    private String email;
    private String name;
    private String phone;
    private String addLine1;
    private String addLine2;
    private String gender;
    private LocalDate dob;
    private String profileImage;

    public GetInfoDto() {};
    public GetInfoDto(String email, String name, String phone, String addLine1, String addLine2, String gender, LocalDate dob, String profileImage) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.addLine1 = addLine1;
        this.addLine2 = addLine2;
        this.gender = gender;
        this.dob = dob;
        this.profileImage = profileImage;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddLine1() {
        return addLine1;
    }

    public String getAddLine2() {
        return addLine2;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDob() {
        return dob;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddLine1(String addLine1) {
        this.addLine1 = addLine1;
    }

    public void setAddLine2(String addLine2) {
        this.addLine2 = addLine2;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
