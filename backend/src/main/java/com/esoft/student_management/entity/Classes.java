package com.esoft.student_management.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "classes")
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CLASS_ID")
    private Integer classId;
    @Column(name = "CLASS_NAME")
    private String className;


    @Column(name = "COUNT")
    @ColumnDefault("30")
    private Integer classCount;

    @Column(name = "AVAILABILITY")
    @ColumnDefault("0")
    private Integer classAvailability;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Student> student;


    @OneToMany(mappedBy = "classes", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Section> sections;

    public Classes() {
    }

    public Classes(Integer classId, String className, Integer classCount, Integer classAvailability, List<Student> student, Set<Section> sections) {
        this.classId = classId;
        this.className = className;
        this.classCount = classCount;
        this.classAvailability = classAvailability;
        this.student = student;
        this.sections = sections;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
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


    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }

    public Integer getClassCount() {
        return classCount;
    }

    public void setClassCount(Integer classCount) {
        this.classCount = classCount;
    }

    public Integer getClassAvailability() {
        return classAvailability;
    }

    public void setClassAvailability(Integer classAvailability) {
        this.classAvailability = classAvailability;
    }
}
