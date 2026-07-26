package com.jobportal.job_portal.dto;

import com.jobportal.job_portal.model.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.List;

public class EmployerApplicationResponse {

    private final Long applicationId;
    private final ApplicationStatus status;
    private final LocalDateTime appliedOn;

    private final Long studentId;
    private final String studentName;
    private final String studentEmail;
    private final String university;
    private final String bio;
    private final String education;
    private final String resumeLink;
    private final boolean availableForOneDay;
    private final List<String> skills;

    public EmployerApplicationResponse(
            Long applicationId,
            ApplicationStatus status,
            LocalDateTime appliedOn,
            Long studentId,
            String studentName,
            String studentEmail,
            String university,
            String bio,
            String education,
            String resumeLink,
            boolean availableForOneDay,
            List<String> skills) {

        this.applicationId = applicationId;
        this.status = status;
        this.appliedOn = appliedOn;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.university = university;
        this.bio = bio;
        this.education = education;
        this.resumeLink = resumeLink;
        this.availableForOneDay = availableForOneDay;
        this.skills = skills;
    }

    public Long getApplicationId() { return applicationId; }
    public ApplicationStatus getStatus() { return status; }
    public LocalDateTime getAppliedOn() { return appliedOn; }
    public Long getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public String getStudentEmail() { return studentEmail; }
    public String getUniversity() { return university; }
    public String getBio() { return bio; }
    public String getEducation() { return education; }
    public String getResumeLink() { return resumeLink; }
    public boolean isAvailableForOneDay() { return availableForOneDay; }
    public List<String> getSkills() { return skills; }
}