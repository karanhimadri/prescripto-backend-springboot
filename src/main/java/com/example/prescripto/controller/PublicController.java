package com.example.prescripto.controller;

import com.example.prescripto.dto.AuthResponseDto;
import com.example.prescripto.dto.PanelLoginRequestDto;
import com.example.prescripto.dto.patient.DoctorDto;
import com.example.prescripto.services.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class PublicController {

    @Autowired
    PublicService publicService;

    @GetMapping("/")
    public String Home() {
        return "Hello, Your Spring Server is Running at PORT 8080.";
    }

    @PostMapping("/panel/login")
    public AuthResponseDto login(@RequestBody PanelLoginRequestDto plrDto) {
        return publicService.panelLogin(plrDto);
    }

    @GetMapping("/all-doctors")
    public List<DoctorDto> getAllDoctorsInfo() {
        return publicService.getAllDoctors();
    }
}
