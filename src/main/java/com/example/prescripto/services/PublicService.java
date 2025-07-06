package com.example.prescripto.services;

import com.example.prescripto.dto.AuthResponseDto;
import com.example.prescripto.dto.PanelLoginRequestDto;
import com.example.prescripto.dto.patient.DoctorDto;
import com.example.prescripto.models.Admin;
import com.example.prescripto.models.doctor.Doctor;
import com.example.prescripto.repository.doctor.DoctorRepository;
import com.example.prescripto.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicService {

    @Autowired DoctorRepository doctorRepository;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired Admin admin;
    @Autowired JwtUtil jwtUtil;

    public AuthResponseDto panelLogin(PanelLoginRequestDto plrDto) {
        if("doctor".equalsIgnoreCase(plrDto.getAuthority())) {
            Optional<Doctor> optionalDoctor = doctorRepository.findById(plrDto.getEmail());

            if(optionalDoctor.isEmpty()) {
                return new AuthResponseDto(false, "Invalid credentials.");
            }

            Doctor doctor = optionalDoctor.get();
            if(!passwordEncoder.matches(plrDto.getPassword(), doctor.getPassword())) {
                return new AuthResponseDto(false, "Invalid credentials.");
            }

            String token = jwtUtil.generateToken(plrDto.getEmail(), "DOCTOR");

            return new AuthResponseDto(true,"Doctor logged in successfully.",doctor.getName(),"DOCTOR",token);
        }

        if(!admin.getEmail().equals(plrDto.getEmail())) {
            return new AuthResponseDto(false, "Invalid credentials.");
        }

        if(!passwordEncoder.matches(plrDto.getPassword(), admin.getPassword())) {
            return new AuthResponseDto(false, "Invalid credentials.");
        }

        String token = jwtUtil.generateToken(plrDto.getEmail(), "ADMIN");
        return new AuthResponseDto(true,"Admin logged in successfully.",admin.getEmail(),"ADMIN",token);
    }

    public List<DoctorDto> getAllDoctors() {
        List<Doctor> DocList = doctorRepository.findAll();

        return DocList.stream().map(doc -> {
            DoctorDto dto = new DoctorDto();
            dto.setEmail(doc.getEmail());
            dto.setName(doc.getName());
            dto.setAvailable(doc.getAvailable());
            dto.setExperience(doc.getExperience());
            dto.setEducation(doc.getEducation());
            dto.setFees(doc.getFees());
            dto.setAbout(doc.getAbout());
            dto.setSpeciality(doc.getSpeciality());
            dto.setAddLine1(doc.getAddLine1());
            dto.setAddLine2(doc.getAddLine2());
            dto.setProfileImage(doc.getProfileImage());
            return dto;
        }).toList();
    }
}
