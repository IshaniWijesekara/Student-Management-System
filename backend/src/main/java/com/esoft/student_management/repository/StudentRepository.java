package com.esoft.student_management.repository;


import com.esoft.student_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByFirstName(String firstName);

    Student findByNic(String nic);

    Student deleteByFirstName(String name);

    Student findByStudentId(Integer id);

    List<Student> findAllByStatus(String status);


    @Query(value = "SELECT * FROM Student s WHERE s.status = 'DEAC'",nativeQuery = true)
    List<Student> findAllBy();


}
