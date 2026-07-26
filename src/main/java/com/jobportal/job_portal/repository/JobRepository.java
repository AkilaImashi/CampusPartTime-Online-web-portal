package com.jobportal.job_portal.repository;

import com.jobportal.job_portal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByEmployer_Id(Long employerId);
}