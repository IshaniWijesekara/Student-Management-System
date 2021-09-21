package com.esoft.student_management.repository;

import com.esoft.student_management.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface ClassesRepository extends JpaRepository<Classes, Integer> {

    Classes deleteByClassName(String className);

    Classes findByClassName(String name);

    @Query(value = "SELECT * FROM Classes",nativeQuery = true)
    List<Classes> findAllBy();

}
