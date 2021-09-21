package com.esoft.student_management.entity;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "registration")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REGISTRATION_ID")
    private Integer registrationId;

    @Column(name = "STUDENT_ID_NF")
    private String studentIdNF;

    @Column(name = "REGISTRATION_DATE")
    private Date registrationDate;


    @Column(name = "STU_NIC")
    private String studentNic;


    public Registration() {
    }

    public Registration(Integer registrationId, String studentIdNF, Date registrationDate, String studentNic) {
        this.registrationId = registrationId;
        this.studentIdNF = studentIdNF;
        this.registrationDate = registrationDate;
        this.studentNic = studentNic;

    }

    public String getStudentIdNF() {
        return studentIdNF;
    }

    public void setStudentIdNF(String studentIdNF) {
        this.studentIdNF = studentIdNF;
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }


    public String getStudentNic() {
        return studentNic;
    }

    public void setStudentNic(String studentNic) {
        this.studentNic = studentNic;
    }
}
