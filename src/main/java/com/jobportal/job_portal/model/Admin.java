package com.jobportal.job_portal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends User {

    @Override
    public String getDashboardInfo() {
        return "Admin dashboard for " + getName();
    }
}