package com.jobportal.job_portal.dto;

import com.jobportal.job_portal.model.ApplicationStatus;

public class ApplicationStatusUpdateRequest {

    private ApplicationStatus status;

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}