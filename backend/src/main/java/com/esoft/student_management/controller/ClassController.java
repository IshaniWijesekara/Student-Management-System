package com.esoft.student_management.controller;


import com.esoft.student_management.dto.ClassDTO;
import com.esoft.student_management.entity.Classes;
import com.esoft.student_management.service.ClassesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${app.endpoint.api}/classes")
public class ClassController {

    final Logger logger = LoggerFactory.getLogger(ClassController.class);

    @Autowired
    private ClassesService classesService;

    @PostMapping("/saveClass")
    public void saveClass(@RequestBody Classes classes) {
        classesService.saveClass(classes);
    }

    @GetMapping("/")
    public List<ClassDTO> findAllClasses() {
        return classesService.getAllClasses();
    }

    @GetMapping("/getClassName/{className}")
    public Classes getProvinceById(@PathVariable String className) {
        return classesService.findByClassName(className);
    }


    @GetMapping("/getClasses")
    public Object getStudents(){
        try {
            List<Object> byId = classesService.getAllClassess();
            return new ResponseEntity(byId, HttpStatus.CREATED);
        } catch (Exception e){
            logger.error("Error when getting class data by id : " +e);
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
