package com.jobportal.job_portal.dto;

import java.util.List;

public class StudentProfileUpdateRequest {

    private String name;
    private String university;
    private String bio;
    private String education;
    private String resumeLink;
    private boolean availableForOneDay;
    private List<String> skills;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }

    public String getResumeLink() { return resumeLink; }
    public void setResumeLink(String resumeLink) { this.resumeLink = resumeLink; }

    public boolean isAvailableForOneDay() { return availableForOneDay; }
    public void setAvailableForOneDay(boolean availableForOneDay) {
        this.availableForOneDay = availableForOneDay;
    }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }
}
