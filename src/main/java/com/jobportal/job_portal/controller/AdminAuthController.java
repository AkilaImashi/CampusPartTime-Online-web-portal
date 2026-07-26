package com.jobportal.job_portal.controller;

import com.jobportal.job_portal.dto.LoginRequest;
import com.jobportal.job_portal.model.Admin;
import com.jobportal.job_portal.service.AdminAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    public AdminAuthController(AdminAuthService adminAuthService) {
        this.adminAuthService = adminAuthService;
    }

    @PostMapping("/login/admin")
    public ResponseEntity<Map<String, Object>> loginAdmin(
            @RequestBody LoginRequest request) {

        Admin admin = adminAuthService.loginAdmin(request);

        return ResponseEntity.ok(Map.of(
                "message", "Admin login successful",
                "adminId", admin.getId(),
                "name", admin.getName(),
                "email", admin.getEmail(),
                "role", admin.getRole().name()
        ));
    }
}