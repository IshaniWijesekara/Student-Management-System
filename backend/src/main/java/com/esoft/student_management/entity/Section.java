package com.esoft.student_management.entity;


import com.esoft.student_management.dto.ClassDTO;
import com.esoft.student_management.dto.LecturerDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SECTION_ID")
    private Integer sectionId;
    @Column(name = "SECTION_NAME")
    private String sectionName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CLASS_ID", nullable = false)
    private Classes classes;
    @Column(name = "ClASS_TIME")
    private String classTime;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LECTURE_ID", nullable = false)
    private Lecture lecture;

    public Section() {
    }

    public Section(String sectionName, Classes classes, String classTime, Lecture lecture) {
        this.sectionName = sectionName;
        this.classes = classes;
        this.classTime = classTime;
        this.lecture = lecture;
    }

    public Section(String sectionName, Classes classes, Lecture lecture) {
        this.sectionName = sectionName;
        this.classes = classes;
        this.lecture = lecture;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }
}
