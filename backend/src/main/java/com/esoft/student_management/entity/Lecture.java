package com.esoft.student_management.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "lecture")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LECTURE_ID")
    private Integer lectureId;
    @Column(name = "LECTURE_NAME")
    private String lectureName;
    @Column(name = "EMAIL")
    private String mail;
    @Column(name = "NIC")
    private String nic;
    @Column(name = "CONTACT_NO")
    private String contactNo;
    @Column(name = "ADDRESS")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SUBJECT_ID", nullable = false)
    private Subject subject;
    @OneToMany(mappedBy = "lecture", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Section> sections;

    public Lecture() {
    }

    public Lecture(String lectureName, String mail, String nic, String contactNo, String address, Subject subject, Set<Section> sections) {
        this.lectureName = lectureName;
        this.mail = mail;
        this.nic = nic;
        this.contactNo = contactNo;
        this.address = address;
        this.subject = subject;
        this.sections = sections;
    }


    public Integer getLectureId() {
        return lectureId;
    }

    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }
}
