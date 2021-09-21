package com.esoft.student_management.dto;

import java.util.List;

public class OrdersDTO {

    private String oid;
    private String orderDate;
    private StudentDTO studentDTO;
    private List<OrderDetailsDTO> orderDetailDTOs;


    public OrdersDTO() {
    }

    public OrdersDTO(String oid, String orderDate, StudentDTO studentDTO, List<OrderDetailsDTO> orderDetailDTOs) {
        this.oid = oid;
        this.orderDate = orderDate;
        this.studentDTO = studentDTO;
        this.orderDetailDTOs = orderDetailDTOs;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public List<OrderDetailsDTO> getOrderDetailDTOs() {
        return orderDetailDTOs;
    }

    public void setOrderDetailDTOs(List<OrderDetailsDTO> orderDetailDTOs) {
        this.orderDetailDTOs = orderDetailDTOs;
    }
}
