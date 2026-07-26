package com.jobportal.job_portal.controller;

import com.jobportal.job_portal.dto.ApplicationStatusUpdateRequest;
import com.jobportal.job_portal.dto.EmployerApplicationResponse;
import com.jobportal.job_portal.service.EmployerApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployerApplicationController {

    private final EmployerApplicationService employerApplicationService;

    public EmployerApplicationController(
            EmployerApplicationService employerApplicationService) {

        this.employerApplicationService = employerApplicationService;
    }

    @GetMapping("/{employerId}/jobs/{jobId}/applicants")
    public List<EmployerApplicationResponse> getApplicants(
            @PathVariable Long employerId,
            @PathVariable Long jobId) {

        return employerApplicationService.getApplicants(employerId, jobId);
    }

    @PutMapping("/{employerId}/jobs/{jobId}/applications/{applicationId}/status")
    public EmployerApplicationResponse updateApplicationStatus(
            @PathVariable Long employerId,
            @PathVariable Long jobId,
            @PathVariable Long applicationId,
            @RequestBody ApplicationStatusUpdateRequest request) {

        return employerApplicationService.updateApplicationStatus(
                employerId,
                jobId,
                applicationId,
                request
        );
    }
}