package com.esoft.student_management.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CODE")
    private Integer code;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    public UserRole() {
    }

    public UserRole(Integer code, String name, Date createdDate) {
        this.code = code;
        this.name = name;
        this.createdDate = createdDate;
    }

    public UserRole(String name, Date createdDate) {
        this.name = name;
        this.createdDate = createdDate;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
