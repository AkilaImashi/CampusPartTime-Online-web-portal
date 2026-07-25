package com.jobportal.job_portal.service;

import com.jobportal.job_portal.dto.AdminUserResponse;
import com.jobportal.job_portal.dto.JobResponse;
import com.jobportal.job_portal.model.User;
import com.jobportal.job_portal.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class AdminService {

    private final UserRepository userRepository;
    private final JobService jobService;

    public AdminService(UserRepository userRepository,
                        JobService jobService) {
        this.userRepository = userRepository;
        this.jobService = jobService;
    }

    @Transactional(readOnly = true)
    public List<AdminUserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toUserResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<JobResponse> getAllJobs() {
        return jobService.getAllJobs();
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));

        userRepository.delete(user);
    }

    public void deleteJob(Long jobId) {
        jobService.deleteJob(jobId);
    }

    private AdminUserResponse toUserResponse(User user) {
        return new AdminUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}