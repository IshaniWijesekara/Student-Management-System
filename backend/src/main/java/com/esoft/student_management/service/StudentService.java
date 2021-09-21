package com.esoft.student_management.service;

import com.esoft.student_management.dto.StudentDTO;
import com.esoft.student_management.entity.Student;


import java.util.ArrayList;
import java.util.List;

public interface StudentService {


    Iterable<Student> findAll();

    Student saveStudent(Student student);

    Student updateStudent(StudentDTO studentDTO);

    boolean updateDetails(StudentDTO studentDTO);

    boolean deleteStudents(Integer id);

    List<StudentDTO> findByNIC(String nic);

    StudentDTO findByStudentNIC(String nic);

    void deleteStudentByName(String name);

    List<StudentDTO> getAllBanks();

    Student findByStudentName(String name);

    StudentDTO findByStudentId(Integer id);

    List<StudentDTO> searchStudent(StudentDTO dto);

    List<StudentDTO> getAll()throws Exception;

    List<Object> getAllEmptyStatusValues();

    public ArrayList<StudentDTO> getAllStudents();


}
