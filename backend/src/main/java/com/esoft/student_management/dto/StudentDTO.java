package com.esoft.student_management.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


public class StudentDTO {
    private int studentId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String address;
    private String schoolName;
    private String gurdianName;
    private String nic;
    private String status;
    private String gurdianContact;
    private String registrationNo;
    private Date birthDay;
    private String classes;
    private String sectionName;
    private Integer lecContact;
    private Date createdTime;
    private Date lasrUpdateTime;
    private int registrationId;
    private Date birthday;
    private Date regDate;
    private String email;



    public StudentDTO() {
    }

    public StudentDTO(int studentId, String firstName, String lastName, String middleName, String address, String schoolName, String gurdianName, String nic, String status, String gurdianContact, String registrationNo, Date birthDay, String classes, String sectionName, Integer lecContact, Date createdTime, Date lasrUpdateTime, int registrationId, Date birthday, Date regDate, String email) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.address = address;
        this.schoolName = schoolName;
        this.gurdianName = gurdianName;
        this.nic = nic;
        this.status = status;
        this.gurdianContact = gurdianContact;
        this.registrationNo = registrationNo;
        this.birthDay = birthDay;
        this.classes = classes;
        this.sectionName = sectionName;
        this.lecContact = lecContact;
        this.createdTime = createdTime;
        this.lasrUpdateTime = lasrUpdateTime;
        this.registrationId = registrationId;
        this.birthday = birthday;
        this.regDate = regDate;
        this.email = email;
    }

    public StudentDTO(Integer studentId, String firstName, String lastName, String middleName, String address, String schoolName, String gurdianName, String nic, String email, String status, String gurdianContact, int registrationId, Date birthDay, Date createdTime, Date lasrUpdateTime) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.address = address;
        this.schoolName = schoolName;
        this.gurdianName = gurdianName;
        this.nic = nic;
        this.status = status;
        this.gurdianContact = gurdianContact;
        this.registrationId = registrationId;
        this.birthDay = birthDay;
        this.createdTime = createdTime;
        this.lasrUpdateTime = lasrUpdateTime;
    }


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getGurdianName() {
        return gurdianName;
    }

    public void setGurdianName(String gurdianName) {
        this.gurdianName = gurdianName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGurdianContact() {
        return gurdianContact;
    }

    public void setGurdianContact(String gurdianContact) {
        this.gurdianContact = gurdianContact;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getLecContact() {
        return lecContact;
    }

    public void setLecContact(Integer lecContact) {
        this.lecContact = lecContact;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLasrUpdateTime() {
        return lasrUpdateTime;
    }

    public void setLasrUpdateTime(Date lasrUpdateTime) {
        this.lasrUpdateTime = lasrUpdateTime;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
