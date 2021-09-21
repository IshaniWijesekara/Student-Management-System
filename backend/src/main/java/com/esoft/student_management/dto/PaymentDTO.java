package com.esoft.student_management.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentDTO {
    private int paymentId;
    private String month;
    private String type;
    private double amount;
    private double fee;
    private Date paymentDate;
    private int studentId;
    private String name;
    private String nic;
    private int year;
    private int class_type;
}
