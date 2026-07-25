package com.jobportal.job_portal.controller;

import com.jobportal.job_portal.dto.AdminUserResponse;
import com.jobportal.job_portal.dto.JobResponse;
import com.jobportal.job_portal.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public List<AdminUserResponse> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/jobs")
    public List<JobResponse> getAllJobs() {
        return adminService.getAllJobs();
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Map<String, String>> deleteUser(
            @PathVariable Long userId) {

        adminService.deleteUser(userId);

        return ResponseEntity.ok(Map.of(
                "message", "User deleted successfully"
        ));
    }

    @DeleteMapping("/jobs/{jobId}")
    public ResponseEntity<Map<String, String>> deleteJob(
            @PathVariable Long jobId) {

        adminService.deleteJob(jobId);

        return ResponseEntity.ok(Map.of(
                "message", "Job deleted successfully"
        ));
    }
}