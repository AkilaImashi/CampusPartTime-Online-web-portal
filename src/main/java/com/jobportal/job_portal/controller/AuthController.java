package com.jobportal.job_portal.controller;

import com.jobportal.job_portal.dto.EmployerRegisterRequest;
import com.jobportal.job_portal.dto.LoginRequest;
import com.jobportal.job_portal.dto.StudentRegisterRequest;
import com.jobportal.job_portal.model.Employer;
import com.jobportal.job_portal.model.Student;
import com.jobportal.job_portal.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register/student")
    public ResponseEntity<Map<String, Object>> registerStudent(
            @RequestBody StudentRegisterRequest request) {

        Student student = authService.registerStudent(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Student registered successfully",
                "studentId", student.getId(),
                "email", student.getEmail()
        ));
    }

    @PostMapping("/login/student")
    public ResponseEntity<Map<String, Object>> loginStudent(
            @RequestBody LoginRequest request) {

        Student student = authService.loginStudent(request);

        return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "studentId", student.getId(),
                "name", student.getName(),
                "email", student.getEmail(),
                "role", student.getRole().name()
        ));
    }

    @PostMapping("/register/employer")
    public ResponseEntity<Map<String, Object>> registerEmployer(
            @RequestBody EmployerRegisterRequest request) {

        Employer employer = authService.registerEmployer(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Employer registered successfully",
                "employerId", employer.getId(),
                "email", employer.getEmail()
        ));
    }

    @PostMapping("/login/employer")
    public ResponseEntity<Map<String, Object>> loginEmployer(
            @RequestBody LoginRequest request) {

        Employer employer = authService.loginEmployer(request);

        return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "employerId", employer.getId(),
                "name", employer.getName(),
                "companyName", employer.getCompanyName(),
                "email", employer.getEmail(),
                "role", employer.getRole().name()
        ));
    }
}