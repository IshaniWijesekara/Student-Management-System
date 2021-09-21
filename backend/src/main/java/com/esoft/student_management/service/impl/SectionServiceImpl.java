package com.esoft.student_management.service.impl;


import com.esoft.student_management.dto.SectionDTO;
import com.esoft.student_management.entity.Classes;
import com.esoft.student_management.entity.Lecture;
import com.esoft.student_management.entity.Section;
import com.esoft.student_management.repository.ClassesRepository;
import com.esoft.student_management.repository.LectureRepository;
import com.esoft.student_management.repository.SectionRepository;
import com.esoft.student_management.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private ClassesRepository classRepository;

    @Override
    public List<SectionDTO> getAllSections() {
        List<Section> status1 = sectionRepository.findAllBy();
        List<SectionDTO> objects = new ArrayList<>();
        for (Section section : status1) {
            SectionDTO section1 = new SectionDTO();
            section1.setSectionId(section.getSectionId());
            section1.setSectionName(section.getSectionName());
            objects.add(section1);
        }

        return objects;
    }

    @Override
    public boolean deleteSection(Integer sectionId) {
       sectionRepository.deleteById(sectionId);
       return true;
    }

    @Override
    public boolean saveSection(SectionDTO sectionDTO) {
        Lecture lecture = lectureRepository.findByLectureName(sectionDTO.getLecture());
        Classes classes = classRepository.findByClassName(sectionDTO.getClasses());
        Section section = new Section(sectionDTO.getSectionName(), classes, lecture);
        sectionRepository.save(section);
        return true;
    }






}
