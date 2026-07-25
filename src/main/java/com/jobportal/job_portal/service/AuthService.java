package com.jobportal.job_portal.service;

import com.jobportal.job_portal.dto.EmployerRegisterRequest;
import com.jobportal.job_portal.dto.LoginRequest;
import com.jobportal.job_portal.dto.StudentRegisterRequest;
import com.jobportal.job_portal.model.Employer;
import com.jobportal.job_portal.model.Role;
import com.jobportal.job_portal.model.Student;
import com.jobportal.job_portal.repository.EmployerRepository;
import com.jobportal.job_portal.repository.StudentRepository;
import com.jobportal.job_portal.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final StudentRepository studentRepository;
    private final EmployerRepository employerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            StudentRepository studentRepository,
            EmployerRepository employerRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.studentRepository = studentRepository;
        this.employerRepository = employerRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Student registerStudent(StudentRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email is already registered"
            );
        }

        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setRole(Role.STUDENT);
        student.setUniversity(request.getUniversity());

        if (request.getSkills() != null) {
            student.setSkills(request.getSkills());
        }

        return studentRepository.save(student);
    }

    public Student loginStudent(LoginRequest request) {
        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid email or password"
                ));

        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid email or password"
            );
        }

        return student;
    }

    public Employer registerEmployer(EmployerRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email is already registered"
            );
        }

        Employer employer = new Employer();
        employer.setName(request.getName());
        employer.setEmail(request.getEmail());
        employer.setPassword(passwordEncoder.encode(request.getPassword()));
        employer.setRole(Role.EMPLOYER);
        employer.setCompanyName(request.getCompanyName());
        employer.setContactNumber(request.getContactNumber());

        return employerRepository.save(employer);
    }

    public Employer loginEmployer(LoginRequest request) {
        Employer employer = employerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid email or password"
                ));

        if (!passwordEncoder.matches(request.getPassword(), employer.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid email or password"
            );
        }

        return employer;
    }
}