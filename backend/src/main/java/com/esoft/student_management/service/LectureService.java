package com.esoft.student_management.service;

import com.esoft.student_management.dto.LecturerDTO;
import com.esoft.student_management.dto.SubjectDTO;

import java.util.List;

public interface LectureService {
    public boolean saveLecture(LecturerDTO lecturerDTO);

    public boolean updateLectureDetails(LecturerDTO lecturerDTO);

    public List<LecturerDTO> getAllLecturers();

    public List<SubjectDTO> getAllSubjects();

    public LecturerDTO getLectureDetails(Integer id);

    List<Object> getAllLectures();
}
