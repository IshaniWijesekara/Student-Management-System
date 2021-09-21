package com.esoft.student_management.service;


import com.esoft.student_management.dto.AttendanceDTO;
import com.esoft.student_management.dto.AttendanceReportDTO;
import com.esoft.student_management.dto.AttendanceSearchDTO;
import com.esoft.student_management.entity.Attendance;
import com.esoft.student_management.entity.Classes;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;


public interface AttendanceService {

    public boolean markAttendance(AttendanceDTO attendanceDTO);

    List<AttendanceDTO> getAttendanceByStudent(Integer studentId) throws Exception;

    List<AttendanceSearchDTO> getAttendanceDetails(AttendanceSearchDTO dto) throws Exception;

    List<AttendanceSearchDTO> getAllAttendance()throws Exception;

    AttendanceSearchDTO getAttendanceDetails(Integer attendanceId) throws Exception;

    Attendance updateAttendance(AttendanceSearchDTO dto)throws Exception;

    List<AttendanceDTO> getAllByClass(String classType)throws Exception;

    List<AttendanceSearchDTO> searchAttendance(AttendanceDTO attendanceDTO)throws Exception;

    ByteArrayOutputStream getAttendaceByMonth(Integer classType, String fromDate, String toDate)throws Exception;

    ByteArrayOutputStream getAttendaceByStudent(Integer classType,String studentId, String fromDate,String toDate)throws Exception;

    ByteArrayOutputStream getLateAttendaceByStudent(Integer classType,String inTime, String fromDate,String toDate)throws Exception;

    ByteArrayOutputStream getEmployeeAttendance(String fromDate,String toDate)throws Exception;

    ByteArrayOutputStream getPaymentProfit(String class_type)throws Exception;


}
