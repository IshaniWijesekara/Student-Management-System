package com.esoft.student_management.service.impl;

import com.esoft.student_management.dto.*;
import com.esoft.student_management.entity.Attendance;
import com.esoft.student_management.entity.Classes;
import com.esoft.student_management.entity.Student;
import com.esoft.student_management.repository.AttendanceRepository;
import com.esoft.student_management.repository.ClassesRepository;
import com.esoft.student_management.repository.StudentRepository;
import com.esoft.student_management.repository.util.OrdersJDBCRepository;
import com.esoft.student_management.repository.util.impl.AttendanceJDBCReppository;
import com.esoft.student_management.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AttendanceJDBCReppository attendanceJDBCReppository;

    @Override
    public boolean markAttendance(AttendanceDTO attendanceDTO) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(attendanceDTO.getDate());
        Integer monthName = Integer.valueOf(new SimpleDateFormat("MM").format(attendanceDTO.getDate().getMonth()));
        String monthString;
        switch (monthName) {
            case 1:  monthString = "January";       break;
            case 2:  monthString = "February";      break;
            case 3:  monthString = "March";         break;
            case 4:  monthString = "April";         break;
            case 5:  monthString = "May";           break;
            case 6:  monthString = "June";          break;
            case 7:  monthString = "July";          break;
            case 8:  monthString = "August";        break;
            case 9:  monthString = "September";     break;
            case 10: monthString = "October";       break;
            case 11: monthString = "November";      break;
            case 12: monthString = "December";      break;
            default: monthString = "Invalid month"; break;
        }

        Student student = studentRepository.findByStudentId(Integer.valueOf(attendanceDTO.getStudentId()));
        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setInTime(attendanceDTO.getInTime());
        attendance.setOutTime(attendanceDTO.getOutTime());
        attendance.setDate(attendanceDTO.getDate());
        attendance.setClassType(classesRepository.findByClassName(attendanceDTO.getClassType()));
        attendance.setMonth(monthString);
        attendanceRepository.save(attendance);
        return true;
    }

    @Override
    public List<AttendanceDTO> getAttendanceByStudent(Integer studentId) throws Exception {
        List<Attendance> all = attendanceRepository.findAllByStudentStudentId(studentId);
        List<AttendanceDTO> list = new ArrayList<>();

        for (Attendance attendance:all) {
            Classes byClassName = classesRepository.findByClassName(attendance.getClassType().getClassName());
            AttendanceDTO attendanceDTO = new AttendanceDTO();
            attendanceDTO.setAttendanceId(attendance.getAttendanceId());
            attendanceDTO.setInTime(attendance.getInTime());
            attendanceDTO.setDate(attendance.getDate());
            attendanceDTO.setOutTime(attendance.getOutTime());
            attendanceDTO.setStudentId(String.valueOf(attendance.getStudent().getStudentId()));
            attendanceDTO.setClassType(byClassName.getClassName());

            list.add(attendanceDTO);
        }
        return list;
    }

    @Override
    public List<AttendanceSearchDTO> getAttendanceDetails(AttendanceSearchDTO dto) throws Exception {
        List<AttendanceSearchDTO> list = new ArrayList<>();
        if (dto.getStudentId() != null && dto.getStudentId() != "") {
            List<Attendance> all = attendanceRepository.findAllByStudentStudentId(Integer.valueOf(dto.getStudentId()));
            for (Attendance attendance:all) {
                Classes byClassName = classesRepository.findByClassName(attendance.getClassType().getClassName());
                Student byStudentId = studentRepository.findByStudentId(attendance.getStudent().getStudentId());
                AttendanceSearchDTO attendanceDTO = new AttendanceSearchDTO();
                attendanceDTO.setStudentId(String.valueOf(attendance.getStudent().getStudentId()));
                attendanceDTO.setAttendanceId(attendance.getAttendanceId());
                attendanceDTO.setInTime(attendance.getInTime());
                attendanceDTO.setDate(attendance.getDate());
                attendanceDTO.setOutTime(attendance.getOutTime());
                attendanceDTO.setFirstName(byStudentId.getFirstName());
                attendanceDTO.setMiddleName(byStudentId.getMiddleName());
                attendanceDTO.setLastName(byStudentId.getLastName());
                attendanceDTO.setNic(byStudentId.getNic());
                attendanceDTO.setClassType(byClassName.getClassName());

                list.add(attendanceDTO);
            }
        }else if (dto.getFirstName() != null) {
            List<Attendance> all = attendanceRepository.findAllByStudentFirstName(dto.getFirstName());
            for (Attendance attendance:all) {
                Classes byClassName = classesRepository.findByClassName(attendance.getClassType().getClassName());
                Student byStudentId = studentRepository.findByFirstName(attendance.getStudent().getFirstName());
                AttendanceSearchDTO attendanceDTO = new AttendanceSearchDTO();
                attendanceDTO.setStudentId(String.valueOf(attendance.getStudent().getStudentId()));
                attendanceDTO.setAttendanceId(attendance.getAttendanceId());
                attendanceDTO.setInTime(attendance.getInTime());
                attendanceDTO.setDate(attendance.getDate());
                attendanceDTO.setOutTime(attendance.getOutTime());
                attendanceDTO.setFirstName(byStudentId.getFirstName());
                attendanceDTO.setMiddleName(byStudentId.getMiddleName());
                attendanceDTO.setLastName(byStudentId.getLastName());
                attendanceDTO.setNic(byStudentId.getNic());
                attendanceDTO.setClassType(byClassName.getClassName());

                list.add(attendanceDTO);
            }
        }else if (dto.getNic() != null) {
            List<Attendance> all = attendanceRepository.findAllByStudentNic(dto.getNic());
            for (Attendance attendance:all) {
                Student byStudentId = studentRepository.findByNic(attendance.getStudent().getNic());
                Classes byClassName = classesRepository.findByClassName(attendance.getClassType().getClassName());
                AttendanceSearchDTO attendanceDTO = new AttendanceSearchDTO();
                attendanceDTO.setStudentId(String.valueOf(attendance.getStudent().getStudentId()));
                attendanceDTO.setAttendanceId(attendance.getAttendanceId());
                attendanceDTO.setInTime(attendance.getInTime());
                attendanceDTO.setDate(attendance.getDate());
                attendanceDTO.setOutTime(attendance.getOutTime());
                attendanceDTO.setFirstName(byStudentId.getFirstName());
                attendanceDTO.setMiddleName(byStudentId.getMiddleName());
                attendanceDTO.setLastName(byStudentId.getLastName());
                attendanceDTO.setNic(byStudentId.getNic());
                attendanceDTO.setClassType(byClassName.getClassName());

                list.add(attendanceDTO);
            }
        }
        return list;
    }

    @Override
    public List<AttendanceSearchDTO> getAllAttendance() throws Exception {
        List<Attendance> all = attendanceRepository.findAll();
        List<AttendanceSearchDTO> list = new ArrayList<>();

        for (Attendance attendance:all) {
            AttendanceSearchDTO attendanceDTO = new AttendanceSearchDTO();
            Classes byClassName = classesRepository.findByClassName(attendance.getClassType().getClassName());
            Student byStudentId = studentRepository.findByNic(attendance.getStudent().getNic());
            attendanceDTO.setStudentId(String.valueOf(attendance.getStudent().getStudentId()));
            attendanceDTO.setAttendanceId(attendance.getAttendanceId());
            attendanceDTO.setInTime(attendance.getInTime());
            attendanceDTO.setDate(attendance.getDate());
            attendanceDTO.setOutTime(attendance.getOutTime());
            attendanceDTO.setFirstName(byStudentId.getFirstName());
            attendanceDTO.setMiddleName(byStudentId.getMiddleName());
            attendanceDTO.setLastName(byStudentId.getLastName());
            attendanceDTO.setNic(byStudentId.getNic());
            attendanceDTO.setClassType(byClassName.getClassName());

            list.add(attendanceDTO);
        }
        return list;
    }

    @Override
    public AttendanceSearchDTO getAttendanceDetails(Integer attendanceId) throws Exception {
        Attendance attendance = attendanceRepository.findByAttendanceId(attendanceId);
        AttendanceSearchDTO attendanceDTO = new AttendanceSearchDTO();
        Student byStudentId = studentRepository.findByNic(attendance.getStudent().getNic());
        Classes byClassName = classesRepository.findByClassName(attendance.getClassType().getClassName());
        attendanceDTO.setStudentId(String.valueOf(attendance.getStudent().getStudentId()));
        attendanceDTO.setAttendanceId(attendance.getAttendanceId());
        attendanceDTO.setInTime(attendance.getInTime());
        attendanceDTO.setDate(attendance.getDate());
        attendanceDTO.setOutTime(attendance.getOutTime());
        attendanceDTO.setFirstName(byStudentId.getFirstName());
        attendanceDTO.setMiddleName(byStudentId.getMiddleName());
        attendanceDTO.setLastName(byStudentId.getLastName());
        attendanceDTO.setNic(byStudentId.getNic());
        attendanceDTO.setClassType(byClassName.getClassName());
        return attendanceDTO;
    }

    @Override
    public Attendance updateAttendance(AttendanceSearchDTO dto) throws Exception {
        Attendance attendances = attendanceRepository.findById(dto.getAttendanceId()).get();
        Classes byClassName = classesRepository.findByClassName(dto.getClassType());
        System.out.println(byClassName.getClassName());
        Student byStudentId = studentRepository.findByNic(dto.getNic());
        attendances.setStudent(byStudentId);
        attendances.setAttendanceId(attendances.getAttendanceId());
        attendances.setInTime(dto.getInTime());
        attendances.setDate(dto.getDate());
        attendances.setOutTime(dto.getOutTime());
        attendances.setClassType(byClassName);
        attendances.setMonth(attendances.getMonth());
        Attendance save = attendanceRepository.save(attendances);
        return save;
    }

    @Override
    public List<AttendanceDTO> getAllByClass(String classType) throws Exception {
        List<AttendanceDTO> attendanceDTOs = new ArrayList<>();
        List<Attendance> allByClassType = attendanceRepository.findAllByClassType(classType);
        for (Attendance attendance:allByClassType) {
            Classes byClassName = classesRepository.findByClassName(attendance.getClassType().getClassName());
            AttendanceDTO attendanceDTO = new AttendanceDTO();
            attendanceDTO.setAttendanceId(attendance.getAttendanceId());
            attendanceDTO.setInTime(attendance.getInTime());
            attendanceDTO.setDate(attendance.getDate());
            attendanceDTO.setOutTime(attendance.getOutTime());
            attendanceDTO.setClassType(byClassName.getClassName());
            attendanceDTO.setStudentId(String.valueOf(attendance.getStudent().getStudentId()));

            attendanceDTOs.add(attendanceDTO);
        }
        return attendanceDTOs;
    }

    @Override
    public List<AttendanceSearchDTO> searchAttendance(AttendanceDTO attendanceDTO) throws Exception {
        if (attendanceDTO != null) {
            List<Predicate> predicates = new ArrayList<>();
            List<AttendanceSearchDTO> results = new ArrayList<>();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Attendance> criteriaQuery = criteriaBuilder.createQuery(Attendance.class);
            Root<Attendance> attendnace = criteriaQuery.from(Attendance.class);

            if (attendanceDTO.getStudentId() != null && (!attendanceDTO.getStudentId().trim().isEmpty())) {
                Predicate studentIdPredicate =
                        criteriaBuilder.equal(attendnace.get("student").get("studentId"), attendanceDTO.getStudentId());

                predicates.add(studentIdPredicate);
            }

            if (attendanceDTO.getClassType() != null  && (!attendanceDTO.getClassType().trim().isEmpty())) {
                Predicate classType =
                        criteriaBuilder.equal(attendnace.get("classType").get("className"), attendanceDTO.getClassType());
                predicates.add(classType);
            }

            if (attendanceDTO.getDate() != null) {
                Predicate date =
                        criteriaBuilder.equal(attendnace.get("date"), attendanceDTO.getDate());

                predicates.add(date);
            }

            if (attendanceDTO.getNic() != null  && (!attendanceDTO.getNic().trim().isEmpty())) {
                Predicate nic =
                        criteriaBuilder.equal(attendnace.get("student").get("nic"), attendanceDTO.getNic());

                predicates.add(nic);
            }

            Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.where(finalPredicate);

            TypedQuery<Attendance> query = entityManager.createQuery(criteriaQuery);

            List<Attendance> resultCities = query.getResultList();
            resultCities.forEach(
                    (attendance) -> {
                        Student byStudentId = studentRepository.findByStudentId(attendance.getStudent().getStudentId());
                        Classes byClassName = classesRepository.findByClassName(attendance.getClassType().getClassName());
                        AttendanceSearchDTO attendanceDTO1 = new AttendanceSearchDTO();
                        attendanceDTO1.setStudentId(String.valueOf(attendance.getStudent().getStudentId()));
                        attendanceDTO1.setAttendanceId(attendance.getAttendanceId());
                        attendanceDTO1.setInTime(attendance.getInTime());
                        attendanceDTO1.setDate(attendance.getDate());
                        attendanceDTO1.setOutTime(attendance.getOutTime());
                        attendanceDTO1.setFirstName(byStudentId.getFirstName());
                        attendanceDTO1.setMiddleName(byStudentId.getMiddleName());
                        attendanceDTO1.setLastName(byStudentId.getLastName());
                        attendanceDTO1.setNic(byStudentId.getNic());
                        attendanceDTO1.setClassType(byClassName.getClassName());
                        results.add(attendanceDTO1);
                    }
            );
            return results;
        }else {
            return null;

        }
    }

    @Override
    public ByteArrayOutputStream getAttendaceByMonth(Integer classType,  String fromDate,String toDate) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        List<AttendanceReportDTO> all = new ArrayList<>();
        List list1 = new ArrayList();
        if (classType != 0 && fromDate != null && toDate != null) {
            List<Map<String, Object>>list = attendanceJDBCReppository.getAttendanceByMonth(String.valueOf(classType),fromDate,toDate);
            for (int i = 0; i < list.size(); i++) {
                AttendanceReportDTO attendanceSearchDTO = new AttendanceReportDTO();
                String studentId = String.valueOf(list.get(i).get("student_id"));
                String fisrtName = String.valueOf(list.get(i).get("first_name"));
                String nic = String.valueOf(list.get(i).get("nic"));
                Date date = (Date) list.get(i).get("date");
                String inTime = String.valueOf(list.get(i).get("in_time"));
                String months = String.valueOf(list.get(i).get("month"));

                attendanceSearchDTO.setStudent_id(studentId);
                attendanceSearchDTO.setFirst_name(fisrtName);
                attendanceSearchDTO.setNic(nic);
                attendanceSearchDTO.setDate(date);
                attendanceSearchDTO.setIn_time(inTime);
                attendanceSearchDTO.setMonth(months);
                all.add(attendanceSearchDTO);
            }
            list1.add(all);
            String reportPath = "E:\\Reports";
            try {

                // Add parameters
                Map<String, Object> parameters = new HashMap<>();

                parameters.put("class_type", classType);
                parameters.put("fromDate", fromDate);
                parameters.put("toDate", toDate);

                JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\report12.jrxml");
                JRDataSource dataSource = new JRBeanCollectionDataSource(list);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
                JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\report12.pdf");

                JRPdfExporter exp = new JRPdfExporter();

                exp.setExporterInput(new SimpleExporterInput(jasperPrint));
                exp.setExporterOutput(new SimpleOutputStreamExporterOutput(output));

                SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
                exp.exportReport();
            } catch (JRException e) {
                e.printStackTrace();
            }

            return output;
        }else {
            return null;
        }
    }

    @Override
    public ByteArrayOutputStream getAttendaceByStudent(Integer classType, String studentId, String fromDate, String toDate) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        List<AttendanceReportDTO> all = new ArrayList<>();
        List list1 = new ArrayList();
        if (classType != 0 && fromDate != null && toDate != null) {
            List<Map<String, Object>>list = attendanceJDBCReppository.getAttendanceByStudent(String.valueOf(classType),studentId,fromDate,toDate);
            for (int i = 0; i < list.size(); i++) {
                AttendanceReportDTO attendanceSearchDTO = new AttendanceReportDTO();
                String studentIds = String.valueOf(list.get(i).get("student_id"));
                String fisrtName = String.valueOf(list.get(i).get("first_name"));
                String nic = String.valueOf(list.get(i).get("nic"));
                Date date = (Date) list.get(i).get("date");
                String inTime = String.valueOf(list.get(i).get("in_time"));
                String months = String.valueOf(list.get(i).get("month"));

                attendanceSearchDTO.setStudent_id(studentIds);
                attendanceSearchDTO.setFirst_name(fisrtName);
                attendanceSearchDTO.setNic(nic);
                attendanceSearchDTO.setDate(date);
                attendanceSearchDTO.setIn_time(inTime);
                attendanceSearchDTO.setMonth(months);
                all.add(attendanceSearchDTO);
            }
            list1.add(all);
            String reportPath = "E:\\Reports";
            try {

                // Add parameters
                Map<String, Object> parameters = new HashMap<>();

                parameters.put("class_type", classType);
                parameters.put("student_id", studentId);
                parameters.put("fromDate", fromDate);
                parameters.put("toDate", toDate);

                JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\report10.jrxml");
                JRDataSource dataSource = new JRBeanCollectionDataSource(list);

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
                JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\report10.pdf");
                JRPdfExporter exp = new JRPdfExporter();

                exp.setExporterInput(new SimpleExporterInput(jasperPrint));
                exp.setExporterOutput(new SimpleOutputStreamExporterOutput(output));

                SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
                exp.exportReport();

            } catch (JRException e) {
                e.printStackTrace();
            }

            return output;
        }else {
            return null;
        }
    }

    @Override
    public ByteArrayOutputStream getLateAttendaceByStudent(Integer classType, String inTime, String fromDate, String toDate) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        List<AttendanceReportDTO> all = new ArrayList<>();
        List list1 = new ArrayList();
        if (classType != 0 && fromDate != null && toDate != null) {
            List<Map<String, Object>>list = attendanceJDBCReppository.getLateAttendanceByStudent(String.valueOf(classType),inTime,fromDate,toDate);
            for (int i = 0; i < list.size(); i++) {
                AttendanceReportDTO attendanceSearchDTO = new AttendanceReportDTO();
                String studentIds = String.valueOf(list.get(i).get("student_id"));
                String fisrtName = String.valueOf(list.get(i).get("first_name"));
                String nic = String.valueOf(list.get(i).get("nic"));
                Date date = (Date) list.get(i).get("date");
                String inTimes = String.valueOf(list.get(i).get("in_time"));
                String months = String.valueOf(list.get(i).get("month"));

                attendanceSearchDTO.setStudent_id(studentIds);
                attendanceSearchDTO.setFirst_name(fisrtName);
                attendanceSearchDTO.setNic(nic);
                attendanceSearchDTO.setDate(date);
                attendanceSearchDTO.setIn_time(inTimes);
                attendanceSearchDTO.setMonth(months);
                all.add(attendanceSearchDTO);
            }
            list1.add(all);
            String reportPath = "E:\\Reports";
            try {

                // Add parameters
                Map<String, Object> parameters = new HashMap<>();

                parameters.put("class_type", classType);
                parameters.put("in_time", inTime);
                parameters.put("fromDate", fromDate);
                parameters.put("toDate", toDate);

                JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\reporAttendancetLate.jrxml");
                // Get your data source
                JRDataSource dataSource = new JRBeanCollectionDataSource(list);

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
                JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\reporAttendancetLate.pdf");
                JRPdfExporter exp = new JRPdfExporter();

                exp.setExporterInput(new SimpleExporterInput(jasperPrint));
                exp.setExporterOutput(new SimpleOutputStreamExporterOutput(output));

                SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
                exp.exportReport();

            } catch (JRException e) {
                e.printStackTrace();
            }

            return output;
        }else {
            return null;
        }
    }

    @Override
    public ByteArrayOutputStream getEmployeeAttendance(String fromDate, String toDate) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        List<EmployeeAttendaceReportDTO> all = new ArrayList<>();
        List list1 = new ArrayList();
        if (fromDate != null && toDate != null) {
            List<Map<String, Object>>list = attendanceJDBCReppository.getEmployeeAttendance(fromDate,toDate);
            for (int i = 0; i < list.size(); i++) {
                EmployeeAttendaceReportDTO attendanceSearchDTO = new EmployeeAttendaceReportDTO();
                String id = String.valueOf(list.get(i).get("id"));
                String name = String.valueOf(list.get(i).get("name"));
                String email = String.valueOf(list.get(i).get("email"));
                String address = String.valueOf(list.get(i).get("address"));
                Date date = (Date) list.get(i).get("date");
                String inTimes = String.valueOf(list.get(i).get("in_time"));
                String outTime = String.valueOf(list.get(i).get("out_time"));

                attendanceSearchDTO.setId(Integer.valueOf(id));
                attendanceSearchDTO.setName(name);
                attendanceSearchDTO.setEmail(email);
                attendanceSearchDTO.setAddress(address);
                attendanceSearchDTO.setDate(date);
                attendanceSearchDTO.setInTime(inTimes);
                attendanceSearchDTO.setOutTime(outTime);
                all.add(attendanceSearchDTO);
            }
            list1.add(all);
            String reportPath = "E:\\Reports";
            try {

                // Add parameters
                Map<String, Object> parameters = new HashMap<>();

                parameters.put("fromDate", fromDate);
                parameters.put("toDate", toDate);

                JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\EmployeeAttendance.jrxml");
                // Get your data source
                JRDataSource dataSource = new JRBeanCollectionDataSource(list);

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
                JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\EmployeeAttendance.pdf");
                JRPdfExporter exp = new JRPdfExporter();

                exp.setExporterInput(new SimpleExporterInput(jasperPrint));
                exp.setExporterOutput(new SimpleOutputStreamExporterOutput(output));

                SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
                exp.exportReport();

            } catch (JRException e) {
                e.printStackTrace();
            }

            return output;
        }else {
            return null;
        }
    }

    @Override
    public ByteArrayOutputStream getPaymentProfit(String class_type) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        List<PaymentReportDTO> all = new ArrayList<>();
        List list1 = new ArrayList();
        if (class_type != null) {
            List<Map<String, Object>>list = attendanceJDBCReppository.getPaymentDetails(class_type);
            for (int i = 0; i < list.size(); i++) {
                PaymentReportDTO attendanceSearchDTO = new PaymentReportDTO();
                String fisrtName = String.valueOf(list.get(i).get("first_name"));
                String email = String.valueOf(list.get(i).get("email"));
                String nic = String.valueOf(list.get(i).get("nic"));
                String fee = String.valueOf(list.get(i).get("fee"));
                Date date = (Date) list.get(i).get("payment_date");
                String type = String.valueOf(list.get(i).get("PAYMENT_TYPE"));

                attendanceSearchDTO.setFirstName(fisrtName);
                attendanceSearchDTO.setEmail(email);
                attendanceSearchDTO.setNic(nic);
                attendanceSearchDTO.setFee(fee);
                attendanceSearchDTO.setPaymentDate(date);
                attendanceSearchDTO.setPaymentType(type);
                all.add(attendanceSearchDTO);
            }
            list1.add(all);
            String reportPath = "E:\\Reports";
            try {

                // Add parameters
                Map<String, Object> parameters = new HashMap<>();

                parameters.put("class_type", class_type);

                JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\PaymentProfit.jrxml");
                // Get your data source
                JRDataSource dataSource = new JRBeanCollectionDataSource(list);

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
                JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\PaymentProfit.pdf");
                JRPdfExporter exp = new JRPdfExporter();

                exp.setExporterInput(new SimpleExporterInput(jasperPrint));
                exp.setExporterOutput(new SimpleOutputStreamExporterOutput(output));

                SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
                exp.exportReport();

            } catch (JRException e) {
                e.printStackTrace();
            }

            return output;
        }else {
            return null;
        }
    }
}
