package com.esoft.student_management.entity;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ATTENDANCE_ID")
    private Integer attendanceId;
    @Column(name = "IN_TIME")
    private String inTime;
    @Column(name = "OUT_TIME")
    private String outTime;
    @Column(name = "DATE")
    private Date date;
    @Column(name = "MONTH")
    private String month;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CLASS_TYPE", nullable = false)
    private Classes classType;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private Student student;


    public Attendance() {
    }

    public Attendance(String inTime, String outTime, Date date, String month, Classes classType, Student student) {
        this.inTime = inTime;
        this.outTime = outTime;
        this.date = date;
        this.month = month;
        this.classType = classType;
        this.student = student;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Classes getClassType() {
        return classType;
    }

    public void setClassType(Classes classType) {
        this.classType = classType;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
