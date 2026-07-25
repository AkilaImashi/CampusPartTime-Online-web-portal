package com.jobportal.job_portal.controller;

import com.jobportal.job_portal.dto.JobRequest;
import com.jobportal.job_portal.dto.JobResponse;
import com.jobportal.job_portal.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobResponse> createJob(
            @RequestBody JobRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobService.createJob(request));
    }

    @GetMapping
    public List<JobResponse> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{jobId}")
    public JobResponse getJobById(@PathVariable Long jobId) {
        return jobService.getJobById(jobId);
    }

    @GetMapping("/employer/{employerId}")
    public List<JobResponse> getJobsByEmployer(
            @PathVariable Long employerId) {

        return jobService.getJobsByEmployer(employerId);
    }

    @PutMapping("/{jobId}")
    public JobResponse updateJob(
            @PathVariable Long jobId,
            @RequestBody JobRequest request) {

        return jobService.updateJob(jobId, request);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<Map<String, String>> deleteJob(
            @PathVariable Long jobId) {

        jobService.deleteJob(jobId);

        return ResponseEntity.ok(Map.of(
                "message", "Job deleted successfully"
        ));
    }
}