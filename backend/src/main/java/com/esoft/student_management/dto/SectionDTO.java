package com.esoft.student_management.dto;


public class SectionDTO {


    private Integer sectionId;
    private String sectionName;
    private String classes;
    private String lecture;


    public SectionDTO() {
    }


    public SectionDTO(Integer sectionId, String sectionName, String classes, String lecture) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.classes = classes;
        this.lecture = lecture;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }
}
