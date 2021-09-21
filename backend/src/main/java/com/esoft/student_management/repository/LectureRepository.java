package com.esoft.student_management.repository;

import com.esoft.student_management.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {

    Lecture findByContactNo(Integer contactNum);

    @Query(value = "SELECT * FROM Lecture",nativeQuery = true)
    List<Lecture> findAllBy();

    Lecture findByLectureName(String name);


}
