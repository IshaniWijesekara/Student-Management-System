package com.esoft.student_management.controller;

import com.esoft.student_management.config.SystemVarList;
import com.esoft.student_management.dto.StudentDTO;
import com.esoft.student_management.entity.Student;
import com.esoft.student_management.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

@RestController
@RequestMapping("${app.endpoint.api}/student")
public class StudentController {
    final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @PostMapping("/saveStudent")
    public void saveStudent(@RequestBody Student student) {
        StudentDTO studentt = studentService.findByStudentNIC(student.getNic());
        if (student.getNic().equals(studentt.getNic())){
            studentService.updateStudent(studentt);
        }else{
            studentService.saveStudent(student);
        }
    }

    @PostMapping("/updateStudent")
    public ResponseEntity save(@RequestBody StudentDTO studentDTO) {
        try {
                studentService.updateStudent(studentDTO);
            return new ResponseEntity<>(SystemVarList.SUCCESS, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error when update student data  : " +e);
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public boolean updateDetails(@RequestBody StudentDTO studentDTO) {
        try {
            studentService.updateDetails(studentDTO);
            return true;
        } catch (Exception e) {
            logger.error("Error when update student details  : " +e);
            return  false;
        }
    }


    @GetMapping("/delete/{id}")
    public boolean deleteStudent(@PathVariable("id") Integer id) {
        try {
            studentService.deleteStudents(id);
            return true;
        } catch (Exception e) {
            logger.error("Error when delete student details  : " +e);
            return  false;
        }
    }

    @GetMapping("/getStudentByNIC/{nic}")
    public ResponseEntity<Student> getStudentByNIC(@PathVariable("nic") String nic ){
        try {
            List<StudentDTO> byNIC = studentService.findByNIC(nic);
            return new ResponseEntity(byNIC, HttpStatus.CREATED);
        } catch (Exception e){
            logger.error("Error when getting student data by nic : " +e);
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getStudent/{id}")
    public StudentDTO getStudentById(@PathVariable("id") Integer id ){
        try {
            return studentService.findByStudentId(id);
        } catch (Exception e){
            logger.error("Error when getting student data by id : " +e);
            return  null;
        }
    }

    @GetMapping("/")
    public List<StudentDTO> getAllStudents() {
        try {
            return studentService.getAll();
        } catch (Exception e) {
        e.printStackTrace();
        logger.error("Error in getting Students: ", e);
        return null;
        }
    }

    @PostMapping("/search")
    public List<StudentDTO> searchStudent(@RequestBody StudentDTO dto) {
        try {
            return studentService.searchStudent(dto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in Searching Student: ", e);
            return null;
        }
    }

    @GetMapping("/getStudents")
    public Object getStudents(){
        try {
            List<Object> byId = studentService.getAllEmptyStatusValues();
            return new ResponseEntity(byId, HttpStatus.CREATED);
        } catch (Exception e){
            logger.error("Error when getting student data by id : " +e);
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
