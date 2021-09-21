package com.esoft.student_management.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class ItemDTO {


    private Integer itemCode;
    private String description;
    private int qty;
    private double unitPrice;

    public ItemDTO() {
    }

    public ItemDTO(Integer itemCode, String description, int qty, double unitPrice) {
        this.itemCode = itemCode;
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }
}
