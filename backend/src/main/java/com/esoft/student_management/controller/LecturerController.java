package com.esoft.student_management.controller;

import com.esoft.student_management.dto.LecturerDTO;
import com.esoft.student_management.dto.SubjectDTO;
import com.esoft.student_management.service.LectureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${app.endpoint.api}/lecturer")
public class LecturerController {
    final Logger logger = LoggerFactory.getLogger(LecturerController.class);

    @Autowired
    private LectureService lectureService;

    @PostMapping("/save")
    public boolean saveLecturer(@RequestBody LecturerDTO lecturerDTO) {
        try {
            return lectureService.saveLecture(lecturerDTO);
        } catch (Exception e){
            logger.error("Error when saving lecturer data : " +e);
            return  false;
        }
    }

    @PostMapping("/update")
    public boolean updateLecture(@RequestBody LecturerDTO lecturerDTO) {
        try {
            return lectureService.updateLectureDetails(lecturerDTO);
        } catch (Exception e){
            logger.error("Error when updating lecturer data : " +e);
            return  false;
        }
    }

    @GetMapping("/")
    public List<LecturerDTO> getAllLecturers() {
        try {
            return lectureService.getAllLecturers();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in getting All Lecturer Details: ", e);
            return null;
        }
    }

    @GetMapping("/getSubjects")
    public List<SubjectDTO> getAllSubjects() {
        try {
            return lectureService.getAllSubjects();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in getting All Subject Details: ", e);
            return null;
        }
    }

    @GetMapping("/getDetails/{id}")
    public LecturerDTO getLectureDetails(@PathVariable("id") Integer id ){
        try {
            return lectureService.getLectureDetails(id);
        } catch (Exception e){
            logger.error("Error when getting lecture data by id : " +e);
            return  null;
        }
    }

}
