package com.example.prescripto.services;

import com.example.prescripto.dto.ApiResponse;
import com.example.prescripto.dto.doctor.DoctorMonthlyOverviewDto;
import com.example.prescripto.dto.doctor.DoctorTotalEarningsDto;
import com.example.prescripto.dto.doctor.TotalAppointmentsDto;
import com.example.prescripto.models.doctor.Doctor;
import com.example.prescripto.models.junctionModel.Appointment;
import com.example.prescripto.models.payment.PaymentInfo;
import com.example.prescripto.repository.doctor.DoctorRepository;
import com.example.prescripto.repository.junctionRepository.AppointmentRepository;
import com.example.prescripto.utils.DateRelatedHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired DoctorRepository doctorRepository;
    @Autowired AppointmentRepository appointmentRepository;
    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);

    public ApiResponse<DoctorTotalEarningsDto> getTotalEarning(String doctorId) {
        Optional<Doctor> optDoctor = doctorRepository.findById(doctorId);

        if(optDoctor.isEmpty()) {
            return new ApiResponse<>(false,"No doctor found.", null);
        }

        int totalEarnings = optDoctor.get().getAppointments().stream()
                .map(Appointment::getPaymentInfo)
                .filter(Objects::nonNull)
                .mapToInt(PaymentInfo::getAmount)
                .sum();

        return new ApiResponse<>(true, "Total earnings fetched.", new DoctorTotalEarningsDto(totalEarnings));
    }

    public ApiResponse<TotalAppointmentsDto> countTotalAppointments(String docId) {
        Optional<Doctor> optDoctor = doctorRepository.findById(docId);

        if(optDoctor.isEmpty()) {
            return new ApiResponse<>(false, "No doctor found.", null);
        }

        long totalAppointments = appointmentRepository.countByDoctorEmail(docId);
        long totalScheduled = appointmentRepository.countByDoctorEmailAndStatus(docId, "SCHEDULED");
        long totalCanceled = appointmentRepository.countByDoctorEmailAndStatus(docId, "CANCELED");
        long totalCompleted = appointmentRepository.countByDoctorEmailAndStatus(docId, "COMPLETED");


        long todayTotalAppointments = appointmentRepository.countByDoctorEmailAndAppointmentDate(docId, LocalDate.now());

        return new ApiResponse<>(true, "Total appointments fetched.",new TotalAppointmentsDto(
                optDoctor.get().getName(), totalAppointments, todayTotalAppointments, totalScheduled, totalCanceled, totalCompleted
        ));
    }

    public ApiResponse<DoctorMonthlyOverviewDto> doctorMonthlyOverview(String docEmail, String month, int year) {
        Optional<Doctor> optDoctor = doctorRepository.findById(docEmail);

        if(optDoctor.isEmpty()) {
            return new ApiResponse<>(false, "No doctor found.", null);
        }

        LocalDate[] dates = DateRelatedHelper.getMonthRange(month, year);

        List<Appointment> appointments = appointmentRepository
                .findByDoctorEmailAndAppointmentDateBetween( docEmail, dates[0], dates[1] );

        int totalEarnings = appointments.stream()
                .map(Appointment :: getPaymentInfo)
                .filter(Objects :: nonNull)
                .mapToInt(PaymentInfo :: getAmount)
                .sum();

        int totalDays = DateRelatedHelper.getTotalDaysInMonth(month, year);
        int totalAppointments = (int) appointmentRepository.countByDoctorEmail(docEmail);

        double rate = totalAppointments > 0 ? ((double) totalAppointments / totalDays) * 100 : 0;
        rate = Math.round(rate * 10.0) / 10.0;
        logger.info("EARN : {} > {} > {} > {}",totalEarnings, totalDays, totalAppointments, rate);

        return new ApiResponse<>(false, "Monthly overview fetched.", new DoctorMonthlyOverviewDto(totalEarnings,rate));
    }
}
