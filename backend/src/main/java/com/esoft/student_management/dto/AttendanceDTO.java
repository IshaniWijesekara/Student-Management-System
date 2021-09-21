package com.esoft.student_management.dto;

import com.esoft.student_management.entity.Student;
import lombok.Data;


import java.util.Date;

@Data
public class AttendanceDTO {

    private Integer attendanceId;
    private String inTime;
    private String outTime;
    private Date date;
    private String studentId;
    private String classType;
    private String nic;
    private String month;

}
