package com.jobportal.job_portal.dto;

import com.jobportal.job_portal.model.ApplicationStatus;
import com.jobportal.job_portal.model.JobType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ApplicationResponse(
        Long applicationId,
        ApplicationStatus status,
        LocalDateTime appliedOn,

        Long jobId,
        String jobTitle,
        String jobDescription,
        JobType jobType,
        BigDecimal pay,
        LocalDate jobDate,
        String location,

        Long employerId,
        String companyName
) {
}