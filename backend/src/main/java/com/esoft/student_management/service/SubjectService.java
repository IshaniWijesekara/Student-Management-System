package com.esoft.student_management.service;

import com.esoft.student_management.dto.LecturerDTO;
import com.esoft.student_management.dto.SubjectDTO;

import java.util.ArrayList;
import java.util.List;

public interface SubjectService {

    public boolean saveSubject(SubjectDTO subjectDTO);

    public boolean updateSubjectDetails(SubjectDTO subjectDTO);


    public ArrayList<SubjectDTO> getAllSubjects();

    public SubjectDTO getSubjectDetails(Integer id);

    public boolean deleteSubject(Integer itemId);


}
