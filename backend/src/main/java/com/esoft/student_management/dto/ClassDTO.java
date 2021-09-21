package com.esoft.student_management.dto;


public class ClassDTO {

    private Integer classId;
    private String className;

    public ClassDTO() {
    }

    public ClassDTO(Integer classId, String className) {
        this.classId = classId;
        this.className = className;
    }


    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
