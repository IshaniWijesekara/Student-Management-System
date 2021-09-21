package com.esoft.student_management.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ITEM_CODE")
    private Integer itemCode;
    @Column(name = "ITEM_NAME")
    private String itemName;
    @Column(name = "QTY")
    private int qty;
    @Column(name = "UNIT_PRICE")
    private double unitPrice;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "order_items",
            joinColumns = {
                    @JoinColumn(name = "ITEM_CODE", referencedColumnName = "ITEM_CODE",
                            nullable = false, updatable = false)},

            inverseJoinColumns = {
                    @JoinColumn(name = "oid", referencedColumnName = "oid",
                            nullable = false, updatable = false)})
            private Set<Orders> orders = new HashSet<>();


    public Item() {
    }

    public Item(Integer itemCode, String itemName, int qty, double unitPrice, Set<Orders> orders) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.orders = orders;
    }

    public Item(Integer itemCode, String itemName, int qty, double unitPrice) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public Integer getItemCode() {
        return itemCode;
    }

    public void setItemCode(Integer itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
}
