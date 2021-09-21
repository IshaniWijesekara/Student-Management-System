package com.esoft.student_management.service.impl;

import com.esoft.student_management.dto.AttendanceDTO;
import com.esoft.student_management.dto.AttendanceSearchDTO;
import com.esoft.student_management.dto.EmployeeAttendanceDTO;
import com.esoft.student_management.entity.Attendance;
import com.esoft.student_management.entity.EmployeeAttendance;
import com.esoft.student_management.entity.User;
import com.esoft.student_management.repository.EmployeeAttendnaceRepository;
import com.esoft.student_management.repository.UserRepository;
import com.esoft.student_management.service.EmployeeAttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
@Transactional
public class EmployeeAttendanceServiceImpl implements EmployeeAttendanceService {
    @Autowired
    private EmployeeAttendnaceRepository employeeAttendnaceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public boolean markAttendance(EmployeeAttendanceDTO attendanceDTO) {
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

        User byUserName = userRepository.findByName(attendanceDTO.getUser());
        EmployeeAttendance employeeAttendance = new EmployeeAttendance();
        employeeAttendance.setDate(attendanceDTO.getDate());
        employeeAttendance.setInTime(attendanceDTO.getInTime());
        employeeAttendance.setOutTime(attendanceDTO.getOutTime());
        employeeAttendance.setMonth(monthString);
        employeeAttendance.setUser(byUserName);
        employeeAttendnaceRepository.save(employeeAttendance);
        return true;
    }

    @Override
    public List<EmployeeAttendanceDTO> getAllAttendances() {
        List<EmployeeAttendance> all = employeeAttendnaceRepository.findAll();
        List<EmployeeAttendanceDTO>list = new ArrayList<>();

        for (EmployeeAttendance employeeAttendance:all) {
            EmployeeAttendanceDTO employeeAttendanceDTO = new EmployeeAttendanceDTO();
            employeeAttendanceDTO.setAttendanceId(employeeAttendance.getAttendanceId());
            employeeAttendanceDTO.setDate(employeeAttendance.getDate());
            employeeAttendanceDTO.setInTime(employeeAttendance.getInTime());
            employeeAttendanceDTO.setOutTime(employeeAttendance.getOutTime());
            employeeAttendanceDTO.setUser(employeeAttendance.getUser().getName());
            employeeAttendanceDTO.setRole(employeeAttendance.getUser().getUserRole().getName());
            list.add(employeeAttendanceDTO);
        }
        return list;
    }

    @Override
    public EmployeeAttendanceDTO getById(Integer id) {
        User user = userRepository.findById(id).get();
        EmployeeAttendanceDTO employeeAttendanceDTO = new EmployeeAttendanceDTO();
        employeeAttendanceDTO.setUser(user.getName());
        employeeAttendanceDTO.setRole(user.getUserRole().getName());
        return employeeAttendanceDTO;
    }

    @Override
    public boolean update(EmployeeAttendanceDTO employeeAttendanceDTO) {
        EmployeeAttendance employeeAttendance = employeeAttendnaceRepository.findById(employeeAttendanceDTO.getAttendanceId()).get();
        User byUserName = userRepository.findByName(employeeAttendanceDTO.getUser());
        employeeAttendance.setDate(employeeAttendanceDTO.getDate());
        employeeAttendance.setInTime(employeeAttendanceDTO.getInTime());
        employeeAttendance.setOutTime(employeeAttendanceDTO.getOutTime());
        employeeAttendance.setMonth(employeeAttendance.getMonth());
        employeeAttendance.setUser(byUserName);
        employeeAttendnaceRepository.save(employeeAttendance);
        return true;
    }

    @Override
    public EmployeeAttendanceDTO getDetails(Integer id) {
        EmployeeAttendance employeeAttendance = employeeAttendnaceRepository.findById(id).get();
        EmployeeAttendanceDTO employeeAttendanceDTO = new EmployeeAttendanceDTO();
        employeeAttendanceDTO.setAttendanceId(employeeAttendance.getAttendanceId());
        employeeAttendanceDTO.setDate(employeeAttendance.getDate());
        employeeAttendanceDTO.setInTime(employeeAttendance.getInTime());
        employeeAttendanceDTO.setOutTime(employeeAttendance.getOutTime());
        employeeAttendanceDTO.setUser(employeeAttendance.getUser().getName());
        employeeAttendanceDTO.setRole(employeeAttendance.getUser().getUserRole().getName());
        return employeeAttendanceDTO;
    }

    @Override
    public List<EmployeeAttendanceDTO> searchEmployee(EmployeeAttendanceDTO dto) {
        if (dto != null) {
            List<Predicate> predicates = new ArrayList<>();
            List<EmployeeAttendanceDTO> results = new ArrayList<>();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<EmployeeAttendance> criteriaQuery = criteriaBuilder.createQuery(EmployeeAttendance.class);
            Root<EmployeeAttendance> attendnace = criteriaQuery.from(EmployeeAttendance.class);

            if (dto.getRole() != null && (!dto.getRole().trim().isEmpty())) {
                Predicate studentIdPredicate =
                        criteriaBuilder.equal(attendnace.get("user").get("userRole").get("name"), dto.getRole());
                predicates.add(studentIdPredicate);
            }

            if (dto.getUser() != null && (!dto.getUser().trim().isEmpty())) {
                Predicate user =
                        criteriaBuilder.like(attendnace.get("user").get("name"), dto.getUser());
                predicates.add(user);
            }

            if (dto.getNic() != null && (!dto.getNic().trim().isEmpty())) {
                Predicate nic =
                        criteriaBuilder.equal(attendnace.get("user").get("nic"), dto.getNic());
                predicates.add(nic);
            }

            Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.where(finalPredicate);

            TypedQuery<EmployeeAttendance> query = entityManager.createQuery(criteriaQuery);

            List<EmployeeAttendance> resultCities = query.getResultList();
            resultCities.forEach(
                    (employeeAttendance) -> {
                        EmployeeAttendanceDTO employeeAttendanceDTO = new EmployeeAttendanceDTO();
                        employeeAttendanceDTO.setAttendanceId(employeeAttendance.getAttendanceId());
                        employeeAttendanceDTO.setDate(employeeAttendance.getDate());
                        employeeAttendanceDTO.setInTime(employeeAttendance.getInTime());
                        employeeAttendanceDTO.setOutTime(employeeAttendance.getOutTime());
                        employeeAttendanceDTO.setUser(employeeAttendance.getUser().getName());
                        employeeAttendanceDTO.setRole(employeeAttendance.getUser().getUserRole().getName());
                        results.add(employeeAttendanceDTO);
                    }
            );
            return results;
        }else {
            return null;
        }
    }
}
