package com.esoft.student_management.service.impl;

import com.esoft.student_management.dto.LecturerDTO;
import com.esoft.student_management.dto.SubjectDTO;
import com.esoft.student_management.entity.Lecture;
import com.esoft.student_management.entity.Subject;
import com.esoft.student_management.repository.LectureRepository;
import com.esoft.student_management.repository.SubjectRepository;
import com.esoft.student_management.service.LectureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class LectureServiceImpl implements LectureService {
    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public boolean saveLecture(LecturerDTO lecturerDTO) {
       if (lecturerDTO != null) {
           Lecture lecture = new Lecture();
           lecture.setAddress(lecturerDTO.getAddress());
           lecture.setContactNo(lecturerDTO.getContactNo());
           lecture.setLectureName(lecturerDTO.getName());
           lecture.setMail(lecturerDTO.getEmail());
           lecture.setNic(lecturerDTO.getNic());
           lecture.setSubject(subjectRepository.findBySubjectName(lecturerDTO.getSubject()));
           lectureRepository.save(lecture);
           return true;
       }else {
           return false;
       }
    }

    @Override
    public boolean updateLectureDetails(LecturerDTO lecturerDTO) {
        if (lecturerDTO != null) {
            Lecture byLectureName = lectureRepository.findByLectureName(lecturerDTO.getName());
            if (byLectureName != null) {
                byLectureName.setSubject(subjectRepository.findBySubjectName(lecturerDTO.getSubject()));
                byLectureName.setMail(lecturerDTO.getEmail());
                byLectureName.setNic(lecturerDTO.getNic());
                byLectureName.setLectureName(lecturerDTO.getName());
                byLectureName.setContactNo(lecturerDTO.getContactNo());
                byLectureName.setAddress(lecturerDTO.getAddress());
                lectureRepository.save(byLectureName);
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    @Override
    public List<LecturerDTO> getAllLecturers() {
        List<Lecture> all = lectureRepository.findAll();
        List<LecturerDTO>list = new ArrayList<>();

        for (Lecture lecture:all) {
            LecturerDTO lecturerDTO = new LecturerDTO();
            lecturerDTO.setAddress(lecture.getAddress());
            lecturerDTO.setContactNo(lecture.getContactNo());
            lecturerDTO.setEmail(lecture.getMail());
            lecturerDTO.setName(lecture.getLectureName());
            lecturerDTO.setNic(lecture.getNic());
            lecturerDTO.setSubject(lecture.getSubject().getSubjectName());
            lecturerDTO.setId(lecture.getLectureId());
            list.add(lecturerDTO);
        }
        return list;
    }

    @Override
    public List<SubjectDTO> getAllSubjects() {
        List<Subject> all = subjectRepository.findAll();
        List<SubjectDTO> list = new ArrayList<>();

        for (Subject subject:all) {
            SubjectDTO subjectDTO = new SubjectDTO();
            subjectDTO.setId(subject.getSubjectId());
            subjectDTO.setName(subject.getSubjectName());
            list.add(subjectDTO);
        }
        return list;
    }

    @Override
    public LecturerDTO getLectureDetails(Integer id) {
        Lecture lecture = lectureRepository.findById(id).get();
        LecturerDTO lecturerDTO = new LecturerDTO();
        lecturerDTO.setNic(lecture.getNic());
        lecturerDTO.setSubject(lecture.getSubject().getSubjectName());
        lecturerDTO.setName(lecture.getLectureName());
        lecturerDTO.setContactNo(lecture.getContactNo());
        lecturerDTO.setEmail(lecture.getMail());
        lecturerDTO.setAddress(lecture.getAddress());
        return lecturerDTO;
    }


    @Override
    public List<Object> getAllLectures() {
        List<Lecture> status1 = lectureRepository.findAllBy();
        List<Object> objects = new ArrayList<>();
        for (Lecture lecture : status1) {
            Lecture lecture1 =new Lecture();
            lecture1.setLectureName(lecture.getLectureName());
            lecture1.setContactNo(lecture.getContactNo());
            lecture1.setMail(lecture.getMail());
            objects.add(lecture1);
        }

        return objects;
    }
}
