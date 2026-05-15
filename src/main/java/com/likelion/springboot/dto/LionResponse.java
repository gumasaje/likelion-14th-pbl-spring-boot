package com.likelion.springboot.dto;

import com.likelion.springboot.domain.role.Lion;

public class LionResponse {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String roleName;
    private String studentId;

    public static LionResponse from(Lion lion) {
        LionResponse lionResponse = new LionResponse();
        lionResponse.name = lion.getName();
        lionResponse.major = lion.getMajor();
        lionResponse.generation = lion.getGeneration();
        lionResponse.part = lion.getPart();
        lionResponse.roleName = lion.roleName();
        lionResponse.studentId = lion.getStudentId();
        return lionResponse;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public int getGeneration() {
        return generation;
    }

    public String getPart() {
        return part;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getStudentId() {
        return studentId;
    }
}
