package com.esoft.student_management.service;

import com.esoft.student_management.dto.SectionDTO;
import java.util.List;

public interface SectionService {

    public boolean saveSection(SectionDTO sectionDTO);

    public List<SectionDTO> getAllSections();

    public boolean deleteSection(Integer sectionId);


}
