package com.esoft.student_management.service.impl;

import com.esoft.student_management.dto.ClassDTO;
import com.esoft.student_management.entity.Classes;
import com.esoft.student_management.repository.ClassesRepository;
import com.esoft.student_management.service.ClassesService;
import com.esoft.student_management.util.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ClassesServiceImpl implements ClassesService {


    @Autowired
    private ClassesRepository classesRepository;




    @Override
    public List<ClassDTO> getAllClasses() {
        List<ClassDTO> list = new ArrayList<>();
        Iterable<Classes> classes = classesRepository.findAll();
        if (classes != null) {
            classes.forEach(allClasses -> {
                ClassDTO classDTO = new ClassDTO();
                classDTO.setClassId(allClasses.getClassId());
                classDTO.setClassName(allClasses.getClassName());
                list.add(classDTO);
            });
        }

        return list;
    }

    @Override
    public Classes saveClass(Classes classes) {
        return classesRepository.save(classes);
    }

    @Override
    public void deleteByClassName(String className) {
        classesRepository.deleteByClassName(className);
    }

    @Override
    public Classes findByClassName(String className) {
       return classesRepository.findByClassName(className);
    }

    @Override
    public List<Object> getAllClassess() {
        List<Classes> status1 = classesRepository.findAllBy();
        List<Object> objects = new ArrayList<>();
        for (Classes classes : status1) {
            Classes classes1 = new Classes();
            classes1.setClassId(classes.getClassId());
            classes1.setClassName(classes.getClassName());
            objects.add(classes1);
        }

        return objects;
    }
}
