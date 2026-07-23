package com.jobportal.job_portal.controller;

import com.jobportal.job_portal.dto.StudentRegisterRequest;
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
}