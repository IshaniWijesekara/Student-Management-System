package com.esoft.student_management.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AttendanceSearchDTO {
    private Integer attendanceId;
    private String inTime;
    private String outTime;
    private Date date;
    private String studentId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String nic;
    private String classType;
    private String month;

    public AttendanceSearchDTO() {
    }

    public AttendanceSearchDTO(Integer attendanceId, String inTime, String outTime, Date date, String studentId, String firstName, String lastName, String middleName, String nic, String classType, String month) {
        this.attendanceId = attendanceId;
        this.inTime = inTime;
        this.outTime = outTime;
        this.date = date;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.nic = nic;
        this.classType = classType;
        this.month = month;
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
