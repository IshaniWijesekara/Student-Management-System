package com.esoft.student_management.entity;

import java.io.Serializable;

/**
 * Created by Elijah-PC on 7/13/2018.
 */
public class OrderDetail_PK implements Serializable{

    String oid;
    Integer iid;


    public OrderDetail_PK() {
    }

    public OrderDetail_PK(String oid, Integer iid) {
        this.oid = oid;
        this.iid = iid;
    }


}
