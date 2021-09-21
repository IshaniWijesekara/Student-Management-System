package com.esoft.student_management.entity;

import org.springframework.dao.DataAccessException;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Elijah-PC on 7/13/2018.
 */
@Entity
public class OrderDetails {

    private int qty;
    private Date orderDate;
    private double totalPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "oid",referencedColumnName = "oid",insertable = false,updatable = false)
    private Orders orders;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_CODE",referencedColumnName = "ITEM_CODE",insertable = false,updatable = false)
    private Item item;
    @EmbeddedId
    private OrderDetail_PK orderDetail_PK;

    public OrderDetails() {
    }

    public OrderDetails(int qty, Date orderDate, double totalPrice, Orders orders, Item item, OrderDetail_PK orderDetail_PK) {
        this.qty = qty;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orders = orders;
        this.item = item;
        this.orderDetail_PK = orderDetail_PK;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public OrderDetail_PK getOrderDetail_PK() {
        return orderDetail_PK;
    }

    public void setOrderDetail_PK(OrderDetail_PK orderDetail_PK) {
        this.orderDetail_PK = orderDetail_PK;
    }


}
