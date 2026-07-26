package com.jobportal.job_portal.service;

import com.jobportal.job_portal.dto.ApplicationStatusUpdateRequest;
import com.jobportal.job_portal.dto.EmployerApplicationResponse;
import com.jobportal.job_portal.model.Application;
import com.jobportal.job_portal.model.Employer;
import com.jobportal.job_portal.model.Job;
import com.jobportal.job_portal.model.Student;
import com.jobportal.job_portal.repository.ApplicationRepository;
import com.jobportal.job_portal.repository.JobRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class EmployerApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;

    public EmployerApplicationService(
            ApplicationRepository applicationRepository,
            JobRepository jobRepository) {

        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
    }

    @Transactional(readOnly = true)
    public List<EmployerApplicationResponse> getApplicants(
            Long employerId,
            Long jobId) {

        verifyEmployerOwnsJob(employerId, jobId);

        return applicationRepository.findByJob_Id(jobId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public EmployerApplicationResponse updateApplicationStatus(
            Long employerId,
            Long jobId,
            Long applicationId,
            ApplicationStatusUpdateRequest request) {

        verifyEmployerOwnsJob(employerId, jobId);

        if (request.getStatus() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Application status is required"
            );
        }

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Application not found"
                ));

        if (!application.getJob().getId().equals(jobId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Application does not belong to this job"
            );
        }

        application.setStatus(request.getStatus());

        return toResponse(applicationRepository.save(application));
    }

    private void verifyEmployerOwnsJob(Long employerId, Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Job not found"
                ));

        Employer employer = job.getEmployer();

        if (!employer.getId().equals(employerId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Employer does not own this job"
            );
        }
    }

    private EmployerApplicationResponse toResponse(Application application) {
        Student student = application.getStudent();

        return new EmployerApplicationResponse(
                application.getId(),
                application.getStatus(),
                application.getAppliedOn(),
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getUniversity(),
                student.getBio(),
                student.getEducation(),
                student.getResumeLink(),
                student.isAvailableForOneDay(),
                student.getSkills()
        );
    }
}