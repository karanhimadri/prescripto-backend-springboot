package com.example.prescripto.services;

import com.example.prescripto.dto.AuthResponseDto;
import com.example.prescripto.dto.BaseResponseDto;
import com.example.prescripto.dto.SuccessAndErrorResDto;
import com.example.prescripto.dto.admin.GeneralResponseForAdminDto;
import com.example.prescripto.dto.patient.GetInfoDto;
import com.example.prescripto.dto.patient.LoginResponseDto;
import com.example.prescripto.models.patient.Patient;
import com.example.prescripto.models.patient.PatientInfo;
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
import java.util.Optional;

@Service
public class PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);
    @Autowired PatientRepository patientRepository;
    @Autowired PatientInfoRepository patientInfoRepository;
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

        if(optPatient.isEmpty() || optPatientInfo.isEmpty()) {
            return new SuccessAndErrorResDto(false,"User not found.");
        }

        PatientInfo patientInfo = optPatientInfo.get();
        Patient patient = optPatient.get();

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

}
