package com.esoft.student_management.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeAttendaceReportDTO {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private Date date;
    private String inTime;
    private String outTime;
}
