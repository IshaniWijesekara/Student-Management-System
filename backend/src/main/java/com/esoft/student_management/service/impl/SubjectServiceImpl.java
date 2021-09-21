package com.esoft.student_management.service.impl;

import com.esoft.student_management.dto.SubjectDTO;
import com.esoft.student_management.entity.Subject;
import com.esoft.student_management.repository.SubjectRepository;
import com.esoft.student_management.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public boolean saveSubject(SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        subject.setSubjectName(subjectDTO.getName());
        subject.setDuration(subjectDTO.getDuration());
        subjectRepository.save(subject);
        return true;
    }

    @Override
    public boolean updateSubjectDetails(SubjectDTO subjectDTO) {
        return false;
    }

    @Override
    public ArrayList<SubjectDTO> getAllSubjects() {
        List<Subject> subject = subjectRepository.findAll();
        ArrayList<SubjectDTO> subjectDTOS = new ArrayList<>();
        for (Subject subject1 : subject) {
            SubjectDTO subjectDTO = new SubjectDTO();
            subjectDTO.setId(subject1.getSubjectId());
            subjectDTO.setName(subject1.getSubjectName());
            subjectDTO.setDuration(subject1.getDuration());
            subjectDTOS.add(subjectDTO);
        }
        return subjectDTOS;
    }

    @Override
    public SubjectDTO getSubjectDetails(Integer id) {
        Subject subject = subjectRepository.findById(id).get();
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(subject.getSubjectId());
        subjectDTO.setName(subject.getSubjectName());
        subjectDTO.setDuration(subject.getDuration());
        return subjectDTO;
    }

    @Override
    public boolean deleteSubject(Integer itemId) {
        subjectRepository.deleteById(itemId);
        return true;
    }
}
