package com.jobportal.job_portal.dto;

import java.util.List;

public class StudentRegisterRequest {

    private String name;
    private String email;
    private String password;
    private String university;
    private List<String> skills;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getUniversity(){
        return university;
    }

    public void setUniversity(String university){
        this.university = university;
    }

    public List<String> getSkills(){
        return skills;
    }

    public void setSkills(List<String> skills){
        this.skills = skills;
    }

}
