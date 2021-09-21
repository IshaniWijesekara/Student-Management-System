package com.esoft.student_management.entity;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "STUDENT_ID")
    private Integer studentId;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "NIC")
    private String nic;
    @Column(name = "SCHOOL_NAME")
    private String schoolName;
    @Column(name = "GURDIAN_NAME")
    private String gurdianName;
    @Column(name = "GURDIAN_Contact")
    private String gurdianContact;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "BIRTH_DAY")
    private Date birthDay;
    @Column(name = "CREATED_TIME")
    private Date createdTime;
    @Column(name = "LAST_UPDATE_TIME")
    private Date lasrUpdateTime;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Attendance> attendances;


//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "REGISTRATION_ID", nullable = false)
    private String registrationId;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Orders> orders;

    @Column(name = "CLASS")
    private String classes;


    public Student() {
    }

    public Student(Integer studentId, String firstName, String lastName, String middleName, String address, String nic, String schoolName, String gurdianName, String gurdianContact, String email, String status, Date birthDay, Date createdTime, Date lasrUpdateTime, Set<Attendance> attendances, String registrationId, Set<Orders> orders, String classes) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.address = address;
        this.nic = nic;
        this.schoolName = schoolName;
        this.gurdianName = gurdianName;
        this.gurdianContact = gurdianContact;
        this.email = email;
        this.status = status;
        this.birthDay = birthDay;
        this.createdTime = createdTime;
        this.lasrUpdateTime = lasrUpdateTime;
        this.attendances = attendances;
        this.registrationId = registrationId;
        this.orders = orders;
        this.classes = classes;
    }

    public Student(int studentId, String firstName, String lastName, String middleName, String address, String nic, String schoolName, String gurdianName, String gurdianContact, String email, String status, Date birthDay, String className, String sectionName, Integer lecContact, Date createdTime, Date lasrUpdateTime) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.address = address;
        this.nic = nic;
        this.schoolName = schoolName;
        this.gurdianName = gurdianName;
        this.gurdianContact = gurdianContact;
        this.status = status;
        this.birthDay = birthDay;
        this.createdTime = createdTime;
        this.lasrUpdateTime = lasrUpdateTime;
        this.attendances = attendances;
        this.registrationId = registrationId;
        this.orders = orders;
        this.classes = classes;
    }


    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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

    public String getGurdianContact() {
        return gurdianContact;
    }

    public void setGurdianContact(String gurdianContact) {
        this.gurdianContact = gurdianContact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Set<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<Attendance> attendances) {
        this.attendances = attendances;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
