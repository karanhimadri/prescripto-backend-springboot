package com.example.prescripto.services;

import com.example.prescripto.dto.AuthResponseDto;
import com.example.prescripto.dto.SuccessAndErrorResDto;
import com.example.prescripto.dto.admin.GeneralResponseForAdminDto;
import com.example.prescripto.dto.doctor.DoctorRequestBodyDto;
import com.example.prescripto.dto.patient.LoginRequestDto;
import com.example.prescripto.models.Admin;
import com.example.prescripto.models.doctor.Doctor;
import com.example.prescripto.repository.doctor.DoctorRepository;
import com.example.prescripto.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired DoctorRepository doctorRepository;
    @Autowired PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    public SuccessAndErrorResDto createDoctorWithImage(DoctorRequestBodyDto dto, MultipartFile file) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(dto.getEmail());

        if (optionalDoctor.isPresent()) {
            return new SuccessAndErrorResDto(false, "Doctor already exists.");
        }

        String hashPassword = passwordEncoder.encode(dto.getPassword());

        Doctor doctor = new Doctor(dto.getEmail(), dto.getName(), hashPassword,"DOCTOR", dto.getExperience(), dto.getFees(), dto.getSpeciality(), dto.getAbout(), dto.getEducation(), dto.getAddLine1(), dto.getAddLine2(), true);

        if (file != null && !file.isEmpty()) {
            // 2MB size limit
            long maxSizeInBytes = 2 * 1024 * 1024;
            if (file.getSize() > maxSizeInBytes) {
                return new SuccessAndErrorResDto(false, "File size exceeds 2MB limit.");
            }

            // Allow only image types
            String contentType = file.getContentType();
            if (contentType == null || (!contentType.equals("image/jpeg")
                    && !contentType.equals("image/png")
                    && !contentType.equals("image/jpg"))) {
                return new SuccessAndErrorResDto(false, "Only JPG, JPEG, or PNG files are allowed.");
            }

            // Save the file
            String uploadDir = "uploads/";
            Path uploadPath = Paths.get(uploadDir);

            try {
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
                String fileName = System.currentTimeMillis() + "_" + originalFileName;

                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                String imageURL = "http://localhost:8080/uploads/" + fileName;
                doctor.setProfileImage(imageURL);
            } catch (IOException e) {
                return new SuccessAndErrorResDto(false, "Image upload failed: " + e.getMessage());
            }
        }

        // Save doctor (with or without image)
        doctorRepository.save(doctor);
        return new SuccessAndErrorResDto(true, "Doctor created successfully.");
    }
}
