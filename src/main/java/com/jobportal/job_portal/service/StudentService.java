package com.jobportal.job_portal.service;

import com.jobportal.job_portal.dto.StudentProfileResponse;
import com.jobportal.job_portal.model.Student;
import com.jobportal.job_portal.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.jobportal.job_portal.dto.StudentProfileUpdateRequest;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentProfileResponse getStudentProfile(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found"
                ));

        return new StudentProfileResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getUniversity(),
                student.getBio(),
                student.getEducation(),
                student.getResumeLink(),
                student.isAvailableForOneDay(),
                student.getSkills()
        );
    }

    public StudentProfileResponse updateStudentProfile(
            Long studentId,
            StudentProfileUpdateRequest request) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found"
                ));

        student.setName(request.getName());
        student.setUniversity(request.getUniversity());
        student.setBio(request.getBio());
        student.setEducation(request.getEducation());
        student.setResumeLink(request.getResumeLink());
        student.setAvailableForOneDay(request.isAvailableForOneDay());
        student.setSkills(request.getSkills());

        Student updatedStudent = studentRepository.save(student);

        return new StudentProfileResponse(
                updatedStudent.getId(),
                updatedStudent.getName(),
                updatedStudent.getEmail(),
                updatedStudent.getUniversity(),
                updatedStudent.getBio(),
                updatedStudent.getEducation(),
                updatedStudent.getResumeLink(),
                updatedStudent.isAvailableForOneDay(),
                updatedStudent.getSkills()
        );
    }
}