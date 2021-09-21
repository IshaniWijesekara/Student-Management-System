package com.esoft.student_management.dto;

public class DailyOrderDTO {

    private String date;
    private String oid;
    private String qty;
    private String totalPrice;
    private String itemName;

    public DailyOrderDTO() {
    }

    public DailyOrderDTO(String date, String oid, String qty, String totalPrice, String itemName) {
        this.date = date;
        this.oid = oid;
        this.qty = qty;
        this.totalPrice = totalPrice;
        this.itemName = itemName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
