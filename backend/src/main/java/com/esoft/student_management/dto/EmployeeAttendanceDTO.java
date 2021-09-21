package com.esoft.student_management.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeAttendanceDTO {
    private Integer attendanceId;
    private String inTime;
    private String outTime;
    private Date date;
    private String user;
    private String nic;
    private String role;
}
