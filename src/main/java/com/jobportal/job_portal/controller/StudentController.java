package com.jobportal.job_portal.controller;

import com.jobportal.job_portal.dto.StudentProfileResponse;
import com.jobportal.job_portal.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.job_portal.dto.StudentProfileUpdateRequest;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public StudentProfileResponse getStudentProfile(
            @PathVariable Long studentId) {
        return studentService.getStudentProfile(studentId);
    }

    @PutMapping("/{studentId}")
    public StudentProfileResponse updateStudentProfile(
            @PathVariable Long studentId,
            @RequestBody StudentProfileUpdateRequest request) {

        return studentService.updateStudentProfile(studentId, request);
    }
}