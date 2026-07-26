package com.jobportal.job_portal.service;

import com.jobportal.job_portal.dto.ApplicationRequest;
import com.jobportal.job_portal.dto.ApplicationResponse;
import com.jobportal.job_portal.model.Application;
import com.jobportal.job_portal.model.ApplicationStatus;
import com.jobportal.job_portal.model.Job;
import com.jobportal.job_portal.model.Student;
import com.jobportal.job_portal.repository.ApplicationRepository;
import com.jobportal.job_portal.repository.JobRepository;
import com.jobportal.job_portal.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final JobRepository jobRepository;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            StudentRepository studentRepository,
            JobRepository jobRepository) {

        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.jobRepository = jobRepository;
    }

    public ApplicationResponse applyToJob(ApplicationRequest request) {

        if (request.studentId() == null || request.jobId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Student ID and job ID are required"
            );
        }

        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found"
                ));

        Job job = jobRepository.findById(request.jobId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Job not found"
                ));

        boolean alreadyApplied = applicationRepository
                .existsByStudent_IdAndJob_Id(student.getId(), job.getId());

        if (alreadyApplied) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "You have already applied for this job"
            );
        }

        Application application = new Application();
        application.setStudent(student);
        application.setJob(job);
        application.setStatus(ApplicationStatus.PENDING);

        Application savedApplication = applicationRepository.save(application);

        return toResponse(savedApplication);
    }

    @Transactional(readOnly = true)
    public List<ApplicationResponse> getStudentApplications(Long studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Student not found"
            );
        }

        return applicationRepository.findByStudent_Id(studentId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ApplicationResponse toResponse(Application application) {

        Job job = application.getJob();

        return new ApplicationResponse(
                application.getId(),
                application.getStatus(),
                application.getAppliedOn(),

                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getType(),
                job.getPay(),
                job.getJobDate(),
                job.getLocation(),

                job.getEmployer().getId(),
                job.getEmployer().getCompanyName()
        );
    }
}