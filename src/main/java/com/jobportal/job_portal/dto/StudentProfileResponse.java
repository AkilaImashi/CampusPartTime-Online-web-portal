package com.jobportal.job_portal.dto;

import java.util.List;

public record StudentProfileResponse(
        Long id,
        String name,
        String email,
        String university,
        String bio,
        String education,
        String resumeLink,
        boolean availableForOneDay,
        List<String> skills
) {
}