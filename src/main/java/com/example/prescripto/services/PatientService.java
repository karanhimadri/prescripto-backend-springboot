package com.example.prescripto.services;

import com.example.prescripto.dto.ApiResponse;
import com.example.prescripto.dto.AuthResponseDto;
import com.example.prescripto.dto.BaseResponseDto;
import com.example.prescripto.dto.SuccessAndErrorResDto;
import com.example.prescripto.dto.appointment.AppointmentBookingRequestDto;
import com.example.prescripto.dto.patient.AppointmentDto;
import com.example.prescripto.dto.patient.GetInfoDto;
import com.example.prescripto.dto.patient.LoginResponseDto;
import com.example.prescripto.models.doctor.Doctor;
import com.example.prescripto.models.junctionModel.Appointment;
import com.example.prescripto.models.patient.Patient;
import com.example.prescripto.models.patient.PatientInfo;
import com.example.prescripto.models.payment.PaymentInfo;
import com.example.prescripto.repository.doctor.DoctorRepository;
import com.example.prescripto.repository.junctionRepository.AppointmentRepository;
import com.example.prescripto.repository.patient.PatientInfoRepository;
import com.example.prescripto.repository.patient.PatientRepository;
import com.example.prescripto.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.*;

@Service
public class PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);
    @Autowired PatientRepository patientRepository;
    @Autowired PatientInfoRepository patientInfoRepository;
    @Autowired DoctorRepository doctorRepository;
    @Autowired AppointmentRepository appointmentRepository;
    @Autowired JwtUtil jwtUtil;
    @Autowired PasswordEncoder passwordEncoder;

    public AuthResponseDto createAccount(Patient patient) {
        if(patientRepository.existsById(patient.getEmail())) {
            return new AuthResponseDto(false, "Email Id already exists.");
        }

        String hashPass = passwordEncoder.encode(patient.getPassword());
        patient.setPassword(hashPass);
        patient.setRole("PATIENT");
        patientRepository.save(patient);
        String token = jwtUtil.generateToken(patient.getEmail(), "PATIENT");

        return new AuthResponseDto(true,"Account created successfully.", patient.getEmail(),"PATIENT",token);
    }

    public LoginResponseDto login(String email, String password) {
        Optional<Patient> optionalPatient = patientRepository.findById(email);
        if(optionalPatient.isEmpty()) {
            return new LoginResponseDto(false, "Invalid email or password.");
        }

        Patient patient = optionalPatient.get();
        if(!passwordEncoder.matches(password, patient.getPassword())) {
            return new LoginResponseDto(false, "Invalid email or password.");
        }
        String token = jwtUtil.generateToken(email, "PATIENT");

        return new LoginResponseDto(true,"User logged in successfully.", email, patient.getName(), token);
    }

    public SuccessAndErrorResDto addOrUpdatePatientInfo(GetInfoDto getInfoDto, String email) {
        Optional<PatientInfo> optionalPatientInfo = patientInfoRepository.findById(email);

        if(optionalPatientInfo.isEmpty()) {
            PatientInfo newInfo = new PatientInfo(getInfoDto.getEmail(), getInfoDto.getPhone(), getInfoDto.getAddLine1(), getInfoDto.getAddLine2(), getInfoDto.getGender(), getInfoDto.getDob(),"");
            Optional<Patient> patient = patientRepository.findById(email);
            patient.ifPresent(value -> newInfo.setFullName(value.getName()));
            patientInfoRepository.save(newInfo);
            return new SuccessAndErrorResDto(true, "Patient information successfully added.");
        }

        PatientInfo existingPatientInfo = optionalPatientInfo.get();

        if(getInfoDto.getEmail() != null) existingPatientInfo.setEmail(email);
        if(getInfoDto.getPhone() != null) existingPatientInfo.setPhone(getInfoDto.getPhone());
        if(getInfoDto.getAddLine1() != null) existingPatientInfo.setAddLine1(getInfoDto.getAddLine1());
        if(getInfoDto.getAddLine2() != null) existingPatientInfo.setAddLine2(getInfoDto.getAddLine2());
        if(getInfoDto.getGender() != null) existingPatientInfo.setGender(getInfoDto.getGender());
        if(getInfoDto.getDob() != null) existingPatientInfo.setDob(getInfoDto.getDob());

        patientInfoRepository.save(existingPatientInfo);
        return new SuccessAndErrorResDto(true, "Patient information successfully updated.");
    }

    public BaseResponseDto getPatientProfile(String email) {
        logger.info("Email : {}", email);
        Optional<Patient> optPatient = patientRepository.findById(email);
        Optional<PatientInfo> optPatientInfo = patientInfoRepository.findById(email);

        if(optPatient.isEmpty()) {
            return new SuccessAndErrorResDto(false,"User not found.");
        }

        Patient patient = optPatient.get();

        if(optPatientInfo.isEmpty()) {
            return new GetInfoDto(patient.getEmail(), patient.getName(), "", "", "", "", null, "");
        }

        PatientInfo patientInfo = optPatientInfo.get();

        return new GetInfoDto(patient.getEmail(), patient.getName(), patientInfo.getPhone(), patientInfo.getAddLine1(), patientInfo.getAddLine2(), patientInfo.getGender(), patientInfo.getDob(), patientInfo.getProfileImage());
    }

    public SuccessAndErrorResDto uploadPatientImage(MultipartFile file, String email) {
        if(file.isEmpty()) {
            return new SuccessAndErrorResDto(false, "Uploaded file is missing");
        }

        // 2MB size limit
        long maxSizeInBytes = 2 * 1024 * 1024;
        if (file.getSize() > maxSizeInBytes) {
            return new SuccessAndErrorResDto(false, "File size exceeds 2MB limit.");
        }

        // Allow only image types
        String contentType = file.getContentType();
        if (contentType == null || (!contentType.equals("image/jpeg") && !contentType.equals("image/png") && !contentType.equals("image/jpg"))) {
            return new SuccessAndErrorResDto(false, "Only JPG, JPEG, or PNG files are allowed.");
        }

        Optional<PatientInfo> optPatient = patientInfoRepository.findById(email);
        if(optPatient.isEmpty()) {
            return new SuccessAndErrorResDto(false, "Patient is not found.");
        }

        PatientInfo patientInfo = optPatient.get();

        // Specify directory
        String UploadDir = "uploads/";
        Path uploadPath = Paths.get(UploadDir);

        try {
            // If directory not exist
            if(!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Sanitize file name and modify the file name
            String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileName = System.currentTimeMillis() + "_" + originalFileName;

            // Copy the original file to new named file
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Update Doctor DB
            String imageURL = "http://localhost:8080/uploads/" + fileName;
            patientInfo.setProfileImage(imageURL);
            patientInfoRepository.save(patientInfo);

            return new SuccessAndErrorResDto(true,fileName + " was successfully uploaded.");

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new SuccessAndErrorResDto(false,"Internal server error.");
        }
    }

    public SuccessAndErrorResDto bookAppointment(AppointmentBookingRequestDto abrDto) {

        Optional<PatientInfo> optPatient = patientInfoRepository.findById(abrDto.getPatientId());
        Optional<Doctor> optDoctor = doctorRepository.findById(abrDto.getDoctorId());

        if(optPatient.isEmpty()) {
            return new SuccessAndErrorResDto(false, "No patient found");
        }
        if(optDoctor.isEmpty()) {
            return new SuccessAndErrorResDto(false, "No doctor found");
        }

        Appointment appointment = new Appointment();

        LocalDate appointmentDate = LocalDate.parse(abrDto.getAppointmentDate());
        appointment.setAppointmentDate(appointmentDate);

        appointment.setAppointmentTime(abrDto.getAppointmentTime());
        appointment.setPatientInfo(optPatient.get());
        appointment.setDoctor(optDoctor.get());
        appointment.setStatus("SCHEDULED");

        appointmentRepository.save(appointment);

        return new SuccessAndErrorResDto(true, "Appointment booked successfully!");
    }

    public ApiResponse<List<AppointmentDto>> getAllAppointments(String patientId) {
        Optional<PatientInfo> optPatient = patientInfoRepository.findById(patientId);

        if (optPatient.isEmpty()) {
            return new ApiResponse<>(false, "No Patient found", Collections.emptyList());
        }

        List<AppointmentDto> appointmentDtos = optPatient.get().getAppointments().stream()
                .map(appointment -> {
                    Doctor doc = appointment.getDoctor();
                    PaymentInfo pay = appointment.getPaymentInfo();
                    return new AppointmentDto(
                            appointment.getId(),
                            doc != null ? doc.getProfileImage() : null,
                            doc != null ? doc.getName() : null,
                            doc != null ? doc.getSpeciality() : null,
                            doc != null ? doc.getFees() : null,
                            doc != null ? doc.getAddLine1() : null,
                            doc != null ? doc.getAddLine2() : null,
                            appointment.getAppointmentDate(),
                            appointment.getAppointmentTime(),
                            pay != null ? pay.getStatus() : null,
                            appointment.getStatus()
                    );
                }).toList();

        return new ApiResponse<>(true, "Appointment Details fetched.", appointmentDtos);
    }

    public SuccessAndErrorResDto cancelAppointment(Long appointmentId) {
        Optional<Appointment> optAppointment = appointmentRepository.findById(appointmentId);

        if(optAppointment.isEmpty()) {
            return new SuccessAndErrorResDto(false, "Appointment not found");
        }

        Appointment appointment = optAppointment.get();

        if ("CANCELED".equalsIgnoreCase(appointment.getStatus())) {
            return new SuccessAndErrorResDto(false, "Appointment is already canceled.");
        }

        appointment.setStatus("CANCELED");
        appointmentRepository.save(appointment);

        return new SuccessAndErrorResDto(true, "Appointment canceled successfully");
    }

}
