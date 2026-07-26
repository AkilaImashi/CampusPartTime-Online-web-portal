package com.jobportal.job_portal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "employers")
public class Employer extends User {

    private String companyName;
    private String contactNumber;

    @Override
    public String getDashboardInfo() {
        return "Employer dashboard for " + getName();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}