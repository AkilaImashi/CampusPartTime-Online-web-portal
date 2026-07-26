package com.jobportal.job_portal.controller;

import com.jobportal.job_portal.dto.ApplicationRequest;
import com.jobportal.job_portal.dto.ApplicationResponse;
import com.jobportal.job_portal.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<ApplicationResponse> applyToJob(
            @RequestBody ApplicationRequest request) {

        ApplicationResponse response = applicationService.applyToJob(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/student/{studentId}")
    public List<ApplicationResponse> getStudentApplications(
            @PathVariable Long studentId) {

        return applicationService.getStudentApplications(studentId);
    }
}