package com.esoft.student_management.controller;


import com.esoft.student_management.entity.Lecture;
import com.esoft.student_management.service.LectureService;
import com.esoft.student_management.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${app.endpoint.api}/lecture")
public class LectureController {

    final Logger logger = LoggerFactory.getLogger(LectureController.class);

    @Autowired
    private LectureService lectureService;

    @GetMapping("/getLectures")
    public Object getStudents(){
        try {
            List<Object> byId = lectureService.getAllLectures();
            return new ResponseEntity(byId, HttpStatus.CREATED);
        } catch (Exception e){
            logger.error("Error when getting lecture data by id : " +e);
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
