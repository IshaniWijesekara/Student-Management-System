package com.esoft.student_management.controller;


import com.esoft.student_management.config.SystemVarList;
import com.esoft.student_management.dto.AttendanceDTO;
import com.esoft.student_management.dto.AttendanceReportDTO;
import com.esoft.student_management.dto.AttendanceSearchDTO;
import com.esoft.student_management.entity.Attendance;
import com.esoft.student_management.service.AttendanceService;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("${app.endpoint.api}/attendance")
public class AttendanceController {

    final Logger logger = LoggerFactory.getLogger(AttendanceController.class);


    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/markAttendance")
    public boolean saveStudent(@RequestBody AttendanceDTO attendanceDTO) {
        try {
            return attendanceService.markAttendance(attendanceDTO);
        } catch (Exception e) {
            logger.error("Error when mark attendance  : " +e);
            return  false;
        }


    }

    @PostMapping("/search")
    public List<AttendanceSearchDTO> searchAttendance(@RequestBody AttendanceSearchDTO dto) {
        try {
            return attendanceService.getAttendanceDetails(dto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in Searching Attendance Details: ", e);
            return null;
        }
    }

    @GetMapping("/")
    public List<AttendanceSearchDTO> getAllAttendances() {
        try {
            return attendanceService.getAllAttendance();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in getting Attendance Details: ", e);
            return null;
        }
    }

    @GetMapping("/getAttendance/{id}")
    public AttendanceSearchDTO getAttendanceById(@PathVariable("id") Integer id ){
        try {
            AttendanceSearchDTO attendanceDetails = attendanceService.getAttendanceDetails(id);
            return attendanceDetails;
        } catch (Exception e){
            logger.error("Error when getting attendance data by id : " +e);
            return null;
        }
    }

    @GetMapping("/getAttendanceByType/{type}")
    public List<AttendanceDTO> getAttendanceByClass(@PathVariable("type") String type ){
        try {
            List<AttendanceDTO> allByClass = attendanceService.getAllByClass(type);
            return allByClass;
        } catch (Exception e){
            logger.error("Error when getting attendance data by Type : " +e);
            return null;
        }
    }

    @PostMapping("/updateAttendance")
    public Attendance updateAttendance(@RequestBody AttendanceSearchDTO attendanceDTO) {
        try {
            return attendanceService.updateAttendance(attendanceDTO);
        } catch (Exception e) {
            logger.error("Error when update attendance  : " +e);
            return null;
        }
    }

    @PostMapping("/searchAttendance")
    public List<AttendanceSearchDTO> searchStudent(@RequestBody AttendanceDTO dto) {
        try {
            return attendanceService.searchAttendance(dto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in Searching Attendance: ", e);
            return null;
        }
    }


    @GetMapping("/getAttendanceByMonth/{classType}/{fromDate}/{toDate}")
    public ResponseEntity<InputStreamResource> getAttendanceByClassType(@PathVariable("classType")Integer classType,@PathVariable("fromDate")String fromDate,@PathVariable("toDate")String toDate)throws IOException, JRException, ParseException {
        try {

            ByteArrayOutputStream output = attendanceService.getAttendaceByMonth(classType,fromDate,toDate);
            InputStream stream = new ByteArrayInputStream(output.toByteArray());
            InputStreamResource resource = new InputStreamResource(stream);

            return ResponseEntity.ok()
                    .contentLength(output.size())
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(resource);
        } catch (Exception e){
            logger.error("Error when getting attendance data by Type and Month : " +e);
            return null;
        }
    }

    @GetMapping("/getAttendanceByStudent/{classType}/{studentId}/{fromDate}/{toDate}")
    public ResponseEntity<InputStreamResource> getAttendanceByStudent(@PathVariable("classType")Integer classType,@PathVariable("studentId")String studentId,@PathVariable("fromDate")String fromDate,@PathVariable("toDate")String toDate){
        try {
            ByteArrayOutputStream output = attendanceService.getAttendaceByStudent(classType,studentId,fromDate,toDate);
            InputStream stream = new ByteArrayInputStream(output.toByteArray());
            InputStreamResource resource = new InputStreamResource(stream);

            return ResponseEntity.ok()
                    .contentLength(output.size())
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(resource);
        } catch (Exception e){
            logger.error("Error when getting attendance data by Student and Month : " +e);
            return null;
        }
    }


    @GetMapping("/getLateAttendance/{classType}/{inTime}/{fromDate}/{toDate}")
    public ResponseEntity<InputStreamResource> getLateAttendanceByStudent(@PathVariable("classType")Integer classType,@PathVariable("inTime")String inTime,@PathVariable("fromDate")String fromDate,@PathVariable("toDate")String toDate){
        try {
            ByteArrayOutputStream output = attendanceService.getLateAttendaceByStudent(classType,inTime,fromDate,toDate);
            InputStream stream = new ByteArrayInputStream(output.toByteArray());
            InputStreamResource resource = new InputStreamResource(stream);

            return ResponseEntity.ok()
                    .contentLength(output.size())
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(resource);
        } catch (Exception e){
            logger.error("Error when getting attendance data by Student and Month : " +e);
            return null;
        }
    }

    @GetMapping("/getEmployeeAttendance/{fromDate}/{toDate}")
    public ResponseEntity<InputStreamResource> getEmployeeAttendance(@PathVariable("fromDate")String fromDate,@PathVariable("toDate")String toDate){
        try {
            ByteArrayOutputStream output = attendanceService.getEmployeeAttendance(fromDate,toDate);
            InputStream stream = new ByteArrayInputStream(output.toByteArray());
            InputStreamResource resource = new InputStreamResource(stream);

            return ResponseEntity.ok()
                    .contentLength(output.size())
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(resource);
        } catch (Exception e){
            logger.error("Error when getting attendance data by Student and Month : " +e);
            return null;
        }
    }

    @GetMapping("/getPaymentProfit/{class_type}")
    public ResponseEntity<InputStreamResource> getPaymentProfit(@PathVariable("class_type")String classType){
        try {
            ByteArrayOutputStream output = attendanceService.getPaymentProfit(classType);
            InputStream stream = new ByteArrayInputStream(output.toByteArray());
            InputStreamResource resource = new InputStreamResource(stream);

            return ResponseEntity.ok()
                    .contentLength(output.size())
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(resource);
        } catch (Exception e){
            logger.error("Error when getting attendance data by Student and Month : " +e);
            return null;
        }
    }
}
