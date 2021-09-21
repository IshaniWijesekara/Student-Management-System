package com.esoft.student_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentDailyOrderDTO {
    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("itemCode")
    private String itemCode;


    public StudentDailyOrderDTO() {
    }

    public StudentDailyOrderDTO(String orderId, String fullName, String itemCode) {
        this.orderId = orderId;
        this.fullName = fullName;
        this.itemCode = itemCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
