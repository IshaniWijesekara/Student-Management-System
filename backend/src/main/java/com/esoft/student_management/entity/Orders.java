package com.esoft.student_management.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "orders")
public class Orders {
    @Id
    private String oid;
    private Date orderDate;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    public Orders() {
    }

    public Orders(String oid, Date orderDate, List<OrderDetails> orderDetails, Student student) {
        this.oid = oid;
        this.orderDate = orderDate;
        this.orderDetails = orderDetails;
        this.student = student;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


}
