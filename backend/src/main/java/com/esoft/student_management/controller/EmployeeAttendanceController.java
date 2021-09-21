package com.esoft.student_management.controller;

import com.esoft.student_management.dto.AttendanceDTO;
import com.esoft.student_management.dto.AttendanceSearchDTO;
import com.esoft.student_management.dto.EmployeeAttendanceDTO;
import com.esoft.student_management.service.EmployeeAttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("${app.endpoint.api}/employee_attendance")
public class EmployeeAttendanceController {
    final Logger logger = LoggerFactory.getLogger(EmployeeAttendanceController.class);

    @Autowired
    private EmployeeAttendanceService employeeAttendanceService;

    @PostMapping("/markAttendance")
    public boolean save(@RequestBody EmployeeAttendanceDTO attendanceDTO) {
        try {
            return employeeAttendanceService.markAttendance(attendanceDTO);
        } catch (Exception e) {
            logger.error("Error when mark attendance  : " +e);
            return  false;
        }
    }


    @PostMapping("/update")
    public boolean update(@RequestBody EmployeeAttendanceDTO attendanceDTO) {
        try {
            return employeeAttendanceService.update(attendanceDTO);
        } catch (Exception e) {
            logger.error("Error when update attendance  : " +e);
            return  false;
        }
    }


    @GetMapping("/")
    public List<EmployeeAttendanceDTO> getAllAttendances() {
        try {
            return employeeAttendanceService.getAllAttendances();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in getting Attendance Details: ", e);
            return null;
        }
    }

    @GetMapping("/getUser/{id}")
    public EmployeeAttendanceDTO getAttendaceById(@PathVariable("id") Integer id ){
        try {
            return employeeAttendanceService.getById(id);
        } catch (Exception e){
            logger.error("Error when getting employee data by id : " +e);
            return  null;
        }
    }

    @GetMapping("/getAttendance/{id}")
    public EmployeeAttendanceDTO getAttendaceByAttendanceId(@PathVariable("id") Integer id ){
        try {
            return employeeAttendanceService.getDetails(id);
        } catch (Exception e){
            logger.error("Error when getting employee data by id : " +e);
            return  null;
        }
    }

    @PostMapping("/searchEmployeeAttendance")
    public List<EmployeeAttendanceDTO> searchStudent(@RequestBody EmployeeAttendanceDTO dto) {
        try {
            return employeeAttendanceService.searchEmployee(dto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in Searching Attendance: ", e);
            return null;
        }
    }

}
