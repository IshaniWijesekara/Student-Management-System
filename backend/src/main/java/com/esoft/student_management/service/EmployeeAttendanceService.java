package com.esoft.student_management.service;
import com.esoft.student_management.dto.EmployeeAttendanceDTO;
import com.esoft.student_management.entity.EmployeeAttendance;

import java.util.List;

public interface EmployeeAttendanceService {
    public boolean markAttendance(EmployeeAttendanceDTO attendanceDTO);

    public List<EmployeeAttendanceDTO>getAllAttendances();

    public EmployeeAttendanceDTO getById(Integer id);

    public boolean update(EmployeeAttendanceDTO employeeAttendanceDTO);

    public EmployeeAttendanceDTO getDetails(Integer id);

    public List<EmployeeAttendanceDTO> searchEmployee(EmployeeAttendanceDTO dto);
}
