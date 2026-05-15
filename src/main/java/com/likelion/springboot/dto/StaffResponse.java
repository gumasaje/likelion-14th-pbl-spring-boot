package com.likelion.springboot.dto;


import com.likelion.springboot.domain.role.Staff;

public class StaffResponse {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String roleName;
    private String position;

    public static StaffResponse from(Staff staff) {
        StaffResponse staffResponse = new StaffResponse();
        staffResponse.name = staff.getName();
        staffResponse.major = staff.getMajor();
        staffResponse.generation = staff.getGeneration();
        staffResponse.part = staff.getPart();
        staffResponse.roleName = staff.roleName();
        staffResponse.position = staff.getPosition();
        return staffResponse;
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

    public String getPosition() {
        return position;
    }
}