package com.esoft.student_management.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SUBJECT_ID")
    private Integer subjectId;
    @Column(name = "SUBJECT_NAME")
    private String subjectName;
    @Column(name = "DURATION")
    private String duration;

    public Subject() {
    }

    public Subject(Integer subjectId, String subjectName, String duration) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.duration = duration;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
