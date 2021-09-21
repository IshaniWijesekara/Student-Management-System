package com.esoft.student_management.repository;


import com.esoft.student_management.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface SectionRepository extends JpaRepository<Section,Integer> {

    Section findBySectionName(String name);


    @Query(value = "SELECT * FROM Section",nativeQuery = true)
    List<Section> findAllBy();

}
