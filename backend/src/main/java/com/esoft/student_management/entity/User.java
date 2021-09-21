package com.esoft.student_management.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NIC")
    private String nic;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "CONTACT_NO")
    private String contactNo;
    @Column(name = "USER_NAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade=CascadeType.ALL)
    @JoinColumn(name = "CODE", nullable = false)
    private UserRole userRole;

    public User() {
    }

    public User(String username, String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public User(Integer id, String name, String email, String nic, String address, String contactNo, String username, String password, Date createdDate, UserRole userRole) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nic = nic;
        this.address = address;
        this.contactNo = contactNo;
        this.username = username;
        this.password = password;
        this.createdDate = createdDate;
        this.userRole = userRole;
    }

    public User(String name, String email, String nic, String address, String contactNo, String username, String password, Date createdDate, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.nic = nic;
        this.address = address;
        this.contactNo = contactNo;
        this.username = username;
        this.password = password;
        this.createdDate = createdDate;
        this.userRole = userRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
