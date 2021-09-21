package com.esoft.student_management.controller;

import com.esoft.student_management.dto.SubjectDTO;
import com.esoft.student_management.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
@RequestMapping("${app.endpoint.api}/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;


    @RequestMapping(value = "/{id}", method = DELETE)
    public boolean deleteSubject(@RequestParam("id") Integer id) {
        return subjectService.deleteSubject(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveSubject(@RequestBody SubjectDTO subjectDTO) {
        return subjectService.saveSubject(subjectDTO);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<SubjectDTO> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    @GetMapping(value = "{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public SubjectDTO getSubject(@PathVariable("id") Integer itemId){
        return subjectService.getSubjectDetails(itemId);
    }


}
