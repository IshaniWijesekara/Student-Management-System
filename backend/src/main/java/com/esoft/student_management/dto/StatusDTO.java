package com.esoft.student_management.dto;


import lombok.Getter;
import lombok.Setter;


public class StatusDTO {

    private String msg;


    public StatusDTO() {
    }

    public StatusDTO(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
