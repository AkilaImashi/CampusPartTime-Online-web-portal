package com.jobportal.job_portal.repository;

import com.jobportal.job_portal.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByJob_Id(Long jobId);
}