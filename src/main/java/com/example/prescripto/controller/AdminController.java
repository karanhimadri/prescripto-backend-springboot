package com.example.prescripto.controller;

import com.example.prescripto.dto.SuccessAndErrorResDto;
import com.example.prescripto.dto.admin.GeneralResponseForAdminDto;
import com.example.prescripto.dto.doctor.DoctorRequestBodyDto;
import com.example.prescripto.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired AdminService adminService;

    @PostMapping(value = "/create-doctor", consumes = "multipart/form-data")
    public SuccessAndErrorResDto createDoctor(@RequestPart("doctor") DoctorRequestBodyDto dto, @RequestPart(value = "file", required = false) MultipartFile file) {
        return adminService.createDoctorWithImage(dto, file);
    }
}
