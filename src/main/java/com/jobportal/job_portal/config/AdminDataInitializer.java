package com.jobportal.job_portal.config;

import com.jobportal.job_portal.model.Admin;
import com.jobportal.job_portal.model.Role;
import com.jobportal.job_portal.repository.AdminRepository;
import com.jobportal.job_portal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminDataInitializer {

    @Bean
    public CommandLineRunner seedAdmin(
            AdminRepository adminRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            String adminEmail = "admin@jobportal.com";

            if (!userRepository.existsByEmail(adminEmail)) {
                Admin admin = new Admin();
                admin.setName("Portal Admin");
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode("Admin123!"));
                admin.setRole(Role.ADMIN);

                adminRepository.save(admin);
            }
        };
    }
}