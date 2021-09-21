package com.esoft.student_management.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AttendanceReportDTO {
    private String student_id;
    private String first_name;
    private String nic;
    private Date date;
    private String in_time;
    private String month;
}
