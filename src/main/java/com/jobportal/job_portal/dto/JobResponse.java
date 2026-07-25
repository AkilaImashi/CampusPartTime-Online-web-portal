package com.jobportal.job_portal.dto;

import com.jobportal.job_portal.model.JobType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JobResponse {

    private final Long id;
    private final String title;
    private final String description;
    private final JobType type;
    private final BigDecimal pay;
    private final LocalDate jobDate;
    private final String location;
    private final LocalDateTime createdAt;
    private final Long employerId;
    private final String employerName;
    private final String companyName;

    public JobResponse(
            Long id,
            String title,
            String description,
            JobType type,
            BigDecimal pay,
            LocalDate jobDate,
            String location,
            LocalDateTime createdAt,
            Long employerId,
            String employerName,
            String companyName) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.pay = pay;
        this.jobDate = jobDate;
        this.location = location;
        this.createdAt = createdAt;
        this.employerId = employerId;
        this.employerName = employerName;
        this.companyName = companyName;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public JobType getType() { return type; }
    public BigDecimal getPay() { return pay; }
    public LocalDate getJobDate() { return jobDate; }
    public String getLocation() { return location; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Long getEmployerId() { return employerId; }
    public String getEmployerName() { return employerName; }
    public String getCompanyName() { return companyName; }
}