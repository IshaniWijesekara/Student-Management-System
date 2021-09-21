package com.esoft.student_management.repository;

import com.esoft.student_management.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    List<Attendance> findAllByStudentStudentId(Integer studentId);

    List<Attendance> findAllByStudentFirstName(String name);

    List<Attendance> findAllByStudentNic(String nic);

    Attendance findByAttendanceId(Integer attendanceId);

    List<Attendance> findAllByClassType(String type);

    List<Attendance> findAllByClassTypeClassIdAndMonth(Integer id, String month);

}
