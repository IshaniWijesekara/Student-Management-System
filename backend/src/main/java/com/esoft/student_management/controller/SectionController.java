package com.esoft.student_management.controller;



import com.esoft.student_management.dto.ItemDTO;
import com.esoft.student_management.dto.SectionDTO;
import com.esoft.student_management.service.LectureService;
import com.esoft.student_management.service.SectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${app.endpoint.api}/section")
public class SectionController {

    final Logger logger = LoggerFactory.getLogger(SectionController.class);

    @Autowired
    private SectionService sectionService;

    @GetMapping("/getSections")
    public Object getStudents(){
        try {
            List<SectionDTO> byId = sectionService.getAllSections();
            return new ResponseEntity(byId, HttpStatus.CREATED);
        } catch (Exception e){
            logger.error("Error when getting section data by id : " +e);
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SectionDTO> getAllItems(){
        return sectionService.getAllSections();
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveSection(@RequestBody SectionDTO sectionDTO){

        return sectionService.saveSection(sectionDTO);
    }


    @DeleteMapping(value = "{sectionId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteItem(Integer sectionId){

        return sectionService.deleteSection(sectionId);
    }




}
