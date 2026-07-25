package com.jobportal.job_portal.dto;

import com.jobportal.job_portal.model.JobType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JobRequest {

    private Long employerId;
    private String title;
    private String description;
    private JobType type;
    private BigDecimal pay;
    private LocalDate jobDate;
    private String location;

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public LocalDate getJobDate() {
        return jobDate;
    }

    public void setJobDate(LocalDate jobDate) {
        this.jobDate = jobDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}