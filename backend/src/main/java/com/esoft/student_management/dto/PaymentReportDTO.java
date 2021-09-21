package com.esoft.student_management.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentReportDTO {
    private String firstName;
    private String email;
    private String nic;
    private String fee;
    private Date paymentDate;
    private String paymentType;
}
