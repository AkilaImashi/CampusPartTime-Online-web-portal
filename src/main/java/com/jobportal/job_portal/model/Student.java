package com.jobportal.job_portal.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends User {

    private String university;
    private String bio;
    private String education;
    private String resumeLink;
    private boolean availableForOneDay;

    @ElementCollection
    @CollectionTable(
            name = "student_skills",
            joinColumns = @JoinColumn(name = "student_id")
    )
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();

    @Override
    public String getDashboardInfo() {
        return "Student dashboard for " + getName();
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getResumeLink() {
        return resumeLink;
    }

    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink;
    }

    public boolean isAvailableForOneDay() {
        return availableForOneDay;
    }

    public void setAvailableForOneDay(boolean availableForOneDay) {
        this.availableForOneDay = availableForOneDay;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}