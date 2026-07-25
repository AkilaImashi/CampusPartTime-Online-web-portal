package com.jobportal.job_portal.service;

import com.jobportal.job_portal.dto.JobRequest;
import com.jobportal.job_portal.dto.JobResponse;
import com.jobportal.job_portal.model.Employer;
import com.jobportal.job_portal.model.Job;
import com.jobportal.job_portal.repository.EmployerRepository;
import com.jobportal.job_portal.repository.JobRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class JobService {

    private final JobRepository jobRepository;
    private final EmployerRepository employerRepository;

    public JobService(JobRepository jobRepository,
                      EmployerRepository employerRepository) {
        this.jobRepository = jobRepository;
        this.employerRepository = employerRepository;
    }

    public JobResponse createJob(JobRequest request) {
        Employer employer = findEmployer(request.getEmployerId());

        Job job = new Job();
        applyRequestToJob(job, request);
        job.setEmployer(employer);

        return toResponse(jobRepository.save(job));
    }

    @Transactional(readOnly = true)
    public List<JobResponse> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public JobResponse getJobById(Long jobId) {
        return toResponse(findJob(jobId));
    }

    @Transactional(readOnly = true)
    public List<JobResponse> getJobsByEmployer(Long employerId) {
        return jobRepository.findByEmployer_Id(employerId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public JobResponse updateJob(Long jobId, JobRequest request) {
        Job job = findJob(jobId);
        applyRequestToJob(job, request);

        return toResponse(jobRepository.save(job));
    }

    public void deleteJob(Long jobId) {
        jobRepository.delete(findJob(jobId));
    }

    private Employer findEmployer(Long employerId) {
        if (employerId == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Employer ID is required"
            );
        }

        return employerRepository.findById(employerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Employer not found"
                ));
    }

    private Job findJob(Long jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Job not found"
                ));
    }

    private void applyRequestToJob(Job job, JobRequest request) {
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setType(request.getType());
        job.setPay(request.getPay());
        job.setJobDate(request.getJobDate());
        job.setLocation(request.getLocation());
    }

    private JobResponse toResponse(Job job) {
        Employer employer = job.getEmployer();

        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getType(),
                job.getPay(),
                job.getJobDate(),
                job.getLocation(),
                job.getCreatedAt(),
                employer.getId(),
                employer.getName(),
                employer.getCompanyName()
        );
    }
}