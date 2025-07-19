package com.example.prescripto.controller;

import com.example.prescripto.dto.ApiResponse;
import com.example.prescripto.dto.doctor.DoctorMonthlyOverviewDto;
import com.example.prescripto.dto.doctor.DoctorTotalEarningsDto;
import com.example.prescripto.dto.doctor.TotalAppointmentsDto;
import com.example.prescripto.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired DoctorService doctorService;

    @GetMapping("/earnings")
    public ApiResponse<DoctorTotalEarningsDto> getEarnings(Authentication auth) {
        return doctorService.getTotalEarning(auth.getName());
    }

    @GetMapping("/total-appointments")
    public ApiResponse<TotalAppointmentsDto> totalAppointments(Authentication auth) {
        return doctorService.countTotalAppointments(auth.getName());
    }

    @GetMapping("/monthly-overview")
    public ApiResponse<DoctorMonthlyOverviewDto> doctorMonthlyOverview(@RequestParam String month, @RequestParam int year, Authentication auth) {
        return doctorService.doctorMonthlyOverview(auth.getName(), month, year);
    }
}
