package com.example.prescripto.controller;

import com.example.prescripto.dto.ApiResponse;
import com.example.prescripto.dto.AuthResponseDto;
import com.example.prescripto.dto.BaseResponseDto;
import com.example.prescripto.dto.SuccessAndErrorResDto;
import com.example.prescripto.dto.appointment.AppointmentBookingRequestDto;
import com.example.prescripto.dto.patient.AppointmentDto;
import com.example.prescripto.dto.patient.GetInfoDto;
import com.example.prescripto.dto.patient.LoginRequestDto;
import com.example.prescripto.dto.patient.LoginResponseDto;
import com.example.prescripto.models.patient.Patient;
import com.example.prescripto.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired PatientService patientService;

    @PostMapping("/create-account")
    public AuthResponseDto createAccount(@RequestBody Patient patient) {
        return patientService.createAccount(patient);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return patientService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }

    @GetMapping("/me")
    public BaseResponseDto getPatientProfile(Authentication authentication) {
        return patientService.getPatientProfile(authentication.getName());
    }

    @PostMapping("/add-or-update")
    public SuccessAndErrorResDto addOrUpdatePatientInfo(@RequestBody GetInfoDto getInfoDto, Authentication authentication) {
        return patientService.addOrUpdatePatientInfo(getInfoDto, authentication.getName());
    }

    @PostMapping("/upload-patient-image")
    public SuccessAndErrorResDto uploadPatientImage(@RequestParam("file")MultipartFile file, Authentication authentication) {
        return patientService.uploadPatientImage(file,authentication.getName());
    }

    @PostMapping("/book-appointment")
    public SuccessAndErrorResDto appointmentBook(@RequestBody AppointmentBookingRequestDto abrDto) {
        return patientService.bookAppointment(abrDto);
    }

    @GetMapping("/all-appointments")
    public ApiResponse<List<AppointmentDto>> getAllAppointments(Authentication auth) {
        return patientService.getAllAppointments(auth.getName());
    }

    @PatchMapping("/cancel-appointment/{id}")
    public SuccessAndErrorResDto cancelAppointment(@PathVariable Long id) {
        return patientService.cancelAppointment(id);
    }

}




