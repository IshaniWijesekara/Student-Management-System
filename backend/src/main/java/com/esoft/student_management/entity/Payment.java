package com.esoft.student_management.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "payment")
public class Payment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PAYMENT_ID")
    private Integer paymentId;
    @Column(name = "January")
    private String january;
    @Column(name = "February")
    private String february;
    @Column(name = "March")
    private String march;
    @Column(name = "April")
    private String april;
    @Column(name = "May")
    private String may;
    @Column(name = "June")
    private String june;
    @Column(name = "July")
    private String july;
    @Column(name = "August")
    private String august;
    @Column(name = "September")
    private String september;
    @Column(name = "Octomber")
    private String octmber;
    @Column(name = "November")
    private String november;
    @Column(name = "December")
    private String december;
    @Column(name = "PAYMENT_TYPE")
    private String paymentType;
    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;
    @Column(name = "AMOUNT")
    private double amount;
    @Column(name = "FEE")
    private double fee;
    @Column(name = "MONTH")
    private String month;
    @Column(name = "YEAR")
    private Integer year;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CLASS_TYPE")
    private Classes class_type;

    public Payment() {
    }

    public Payment(String january, String february, String march, String april, String may, String june, String july, String august, String september, String octmber, String november, String december, String paymentType, Date paymentDate, double amount, double fee, String month, Integer year, Student student, Classes class_type) {
        this.january = january;
        this.february = february;
        this.march = march;
        this.april = april;
        this.may = may;
        this.june = june;
        this.july = july;
        this.august = august;
        this.september = september;
        this.octmber = octmber;
        this.november = november;
        this.december = december;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.fee = fee;
        this.month = month;
        this.year = year;
        this.student = student;
        this.class_type = class_type;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getJanuary() {
        return january;
    }

    public void setJanuary(String january) {
        this.january = january;
    }

    public String getFebruary() {
        return february;
    }

    public void setFebruary(String february) {
        this.february = february;
    }

    public String getMarch() {
        return march;
    }

    public void setMarch(String march) {
        this.march = march;
    }

    public String getApril() {
        return april;
    }

    public void setApril(String april) {
        this.april = april;
    }

    public String getMay() {
        return may;
    }

    public void setMay(String may) {
        this.may = may;
    }

    public String getJune() {
        return june;
    }

    public void setJune(String june) {
        this.june = june;
    }

    public String getJuly() {
        return july;
    }

    public void setJuly(String july) {
        this.july = july;
    }

    public String getAugust() {
        return august;
    }

    public void setAugust(String august) {
        this.august = august;
    }

    public String getSeptember() {
        return september;
    }

    public void setSeptember(String september) {
        this.september = september;
    }

    public String getOctmber() {
        return octmber;
    }

    public void setOctmber(String octmber) {
        this.octmber = octmber;
    }

    public String getNovember() {
        return november;
    }

    public void setNovember(String november) {
        this.november = november;
    }

    public String getDecember() {
        return december;
    }

    public void setDecember(String december) {
        this.december = december;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Classes getClass_type() {
        return class_type;
    }

    public void setClass_type(Classes class_type) {
        this.class_type = class_type;
    }
}
