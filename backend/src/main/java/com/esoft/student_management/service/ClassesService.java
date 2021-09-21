package com.esoft.student_management.service;

import com.esoft.student_management.dto.ClassDTO;
import com.esoft.student_management.entity.Classes;

import java.util.List;

public interface ClassesService {

    public List<ClassDTO> getAllClasses();

    public Classes saveClass(Classes classes);

    public void deleteByClassName(String className);

    public Classes findByClassName(String className);


    List<Object> getAllClassess();


}
