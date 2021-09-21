package com.esoft.student_management.service.impl;

import com.esoft.student_management.dto.StudentDTO;
import com.esoft.student_management.entity.*;
import com.esoft.student_management.repository.*;
import com.esoft.student_management.service.StudentService;
import com.esoft.student_management.util.EmailService;
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
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private LectureRepository lectureRepository;


    @Override
    public Iterable<Student> findAll() {
        return null;
    }

    @Override
    public Student saveStudent(Student student) {
        Registration registration2 = new Registration();
        Student studentN = new Student();
        studentN.setAddress(student.getAddress());
        studentN.setFirstName(student.getFirstName());
        studentN.setGurdianContact(student.getGurdianContact());
        studentN.setLastName(student.getLastName());
        studentN.setNic(student.getNic());
        studentN.setEmail(student.getEmail());
        studentN.setStatus("DEAC");
        studentN.setGurdianName(student.getGurdianName());
        studentN.setMiddleName(student.getMiddleName());
        studentN.setRegistrationId(String.valueOf(""));
        studentN.setSchoolName(student.getSchoolName());
        studentN.setBirthDay(student.getBirthDay());
        studentN.setCreatedTime(new Date());
        studentN.setLasrUpdateTime(new Date());
        studentN.setClasses(student.getClasses());

        registration2.setRegistrationDate(new Date());
        registration2.setStudentNic(student.getNic());
        registrationRepository.save(registration2);


        return studentRepository.save(studentN);

    }

    @Override
    public Student updateStudent(StudentDTO studentDTO) {
        System.out.println(studentDTO+"");
        Student byStudentId = studentRepository.findByStudentId(studentDTO.getStudentId());

        Section section = sectionRepository.findBySectionName(studentDTO.getSectionName());

        Registration registration = registrationRepository.findByStudentNic(studentDTO.getNic());

        Lecture lecture = lectureRepository.findByContactNo(studentDTO.getLecContact());


        if (byStudentId != null) {
            if (byStudentId.getStudentId().equals(studentDTO.getStudentId())) {

                //Register Student
                if (registration.getStudentNic().equals(studentDTO.getNic())) {

                    Registration registration1 = new Registration();
                    registration1.setRegistrationId(registration.getRegistrationId());
                    registration1.setRegistrationDate(new Date());
                    registration1.setStudentIdNF("STU" + studentDTO.getStudentId());
                    registration1.setStudentNic(studentDTO.getNic());
                    registrationRepository.save(registration1);

                } else {
                    Registration registration2 = new Registration();
                    registration2.setRegistrationDate(new Date());
                    registration2.setStudentNic(studentDTO.getNic());
                    registrationRepository.save(registration2);
                }
                if (studentDTO.getClasses() != null) {
                    Classes classes = classesRepository.findByClassName(studentDTO.getClasses());
                    if (classes.getClassCount() > classes.getClassAvailability()) {
                        Classes classes1 = new Classes();
                        classes1.setClassId(classes.getClassId());
                        classes1.setClassAvailability(classes.getClassAvailability() + 1);
                        classes1.setClassCount(classes.getClassCount());
                        classes1.setClassName(classes.getClassName());
                        classes1.setSections(Collections.singleton(section));
                        //emailService.sendMail(lecture.getMail(), "New Class Registration", studentDTO.getFirstName() + " " + studentDTO.getLastName() + " Add " + classes.getClassName());
                        classesRepository.save(classes1);
                    }
                }

            }
            byStudentId.setStudentId(studentDTO.getStudentId());
            byStudentId.setAddress(studentDTO.getAddress());
            byStudentId.setFirstName(studentDTO.getFirstName());
            byStudentId.setGurdianContact(studentDTO.getGurdianContact());
            byStudentId.setLastName(studentDTO.getLastName());
            byStudentId.setGurdianName(studentDTO.getGurdianName());
            byStudentId.setMiddleName(studentDTO.getMiddleName());
            byStudentId.setStatus(studentDTO.getStatus());
            byStudentId.setNic(studentDTO.getNic());
            byStudentId.setEmail(studentDTO.getEmail());
            byStudentId.setRegistrationId("STU" + studentDTO.getStudentId());
            byStudentId.setStatus("ACT");
            byStudentId.setSchoolName(studentDTO.getSchoolName());
            byStudentId.setBirthDay(studentDTO.getBirthDay());
            byStudentId.setCreatedTime(new Date());
            byStudentId.setLasrUpdateTime(byStudentId.getLasrUpdateTime());
            return studentRepository.save(byStudentId);


        } else {
            Registration registration2 = new Registration();
            Student student = new Student();
            student.setAddress(studentDTO.getAddress());
            student.setFirstName(studentDTO.getFirstName());
            student.setGurdianContact(studentDTO.getGurdianContact());
            student.setLastName(studentDTO.getLastName());
            student.setNic(studentDTO.getNic());
            student.setEmail(studentDTO.getEmail());
            student.setStatus("DEAC");
            student.setGurdianName(studentDTO.getGurdianName());
            student.setMiddleName(studentDTO.getMiddleName());
            student.setRegistrationId(String.valueOf(studentDTO.getRegistrationNo()));
            student.setSchoolName(studentDTO.getSchoolName());
            student.setBirthDay(studentDTO.getBirthDay());
            student.setCreatedTime(new Date());
            student.setLasrUpdateTime(new Date());
            registration2.setRegistrationDate(new Date());
            registration2.setStudentNic(studentDTO.getNic());
            registrationRepository.save(registration2);

            return studentRepository.save(student);
        }
    }

    @Override
    public boolean updateDetails(StudentDTO studentDTO) {
        Student byStudentId = studentRepository.findByStudentId(studentDTO.getRegistrationId());
        byStudentId.setStudentId(studentDTO.getRegistrationId());
        byStudentId.setAddress(studentDTO.getAddress());
        byStudentId.setFirstName(studentDTO.getFirstName());
        byStudentId.setGurdianContact(studentDTO.getGurdianContact());
        byStudentId.setLastName(studentDTO.getLastName());
        byStudentId.setGurdianName(studentDTO.getGurdianName());
        byStudentId.setMiddleName(studentDTO.getMiddleName());
        byStudentId.setStatus(studentDTO.getStatus());
        byStudentId.setNic(studentDTO.getNic());
        byStudentId.setEmail(studentDTO.getEmail());
        byStudentId.setStatus("ACT");
        byStudentId.setSchoolName(studentDTO.getSchoolName());
        byStudentId.setBirthDay(studentDTO.getBirthday());
        byStudentId.setCreatedTime(new Date());
        studentRepository.save(byStudentId);
        return true;
    }

    @Override
    public boolean deleteStudents(Integer id) {
        Student student = studentRepository.findById(id).get();
        student.setStatus("DEAC");
        studentRepository.save(student);
        return true;
    }

    @Override
    public List<StudentDTO> findByNIC(String nic) {
        List<StudentDTO> list = new ArrayList<>();;
        Student byNic = studentRepository.findByNic(nic);
        Registration registration = registrationRepository.findByStudentIdNFLike(byNic.getRegistrationId());
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setAddress(byNic.getAddress());
        studentDTO.setFirstName(byNic.getFirstName());
        studentDTO.setGurdianContact(byNic.getGurdianContact());
        studentDTO.setLastName(byNic.getLastName());
        studentDTO.setNic(byNic.getNic());
        studentDTO.setBirthday(byNic.getBirthDay());
        studentDTO.setGurdianName(byNic.getGurdianName());
        studentDTO.setMiddleName(byNic.getMiddleName());
        studentDTO.setStatus("ACT");
        studentDTO.setRegistrationId(registration.getRegistrationId());
        studentDTO.setSchoolName(byNic.getSchoolName());
        studentDTO.setEmail(byNic.getEmail());
        list.add(studentDTO);

        return list;
    }

    @Override
    public StudentDTO findByStudentNIC(String nic) {
        Student byNic = studentRepository.findByNic(nic);
        StudentDTO studentDTO =new StudentDTO();
        if (byNic != null) {
            studentDTO.setStudentId(byNic.getStudentId());
            studentDTO.setClasses(byNic.getClasses());
            studentDTO.setAddress(byNic.getAddress());
            studentDTO.setFirstName(byNic.getFirstName());
            studentDTO.setGurdianContact(byNic.getGurdianContact());
            studentDTO.setLastName(byNic.getLastName());
            studentDTO.setNic(byNic.getNic());
            studentDTO.setGurdianName(byNic.getGurdianName());
            studentDTO.setMiddleName(byNic.getMiddleName());
            studentDTO.setStatus(byNic.getStatus());
            if (byNic.getRegistrationId() != null) {
                studentDTO.setRegistrationNo(byNic.getRegistrationId());
            } else {
                studentDTO.setRegistrationNo("");
            }
            studentDTO.setSchoolName(byNic.getSchoolName());
            return studentDTO;
        }


        return new StudentDTO();
    }

    @Override
    public void deleteStudentByName(String name) {
        studentRepository.deleteByFirstName(name);
    }

    @Override
    public List<StudentDTO> getAllBanks() {
        return null;
    }

    @Override
    public Student findByStudentName(String name) {
       return studentRepository.findByFirstName(name);
    }

    @Override
    public StudentDTO findByStudentId(Integer id) {
        Student byStudentId = studentRepository.findByStudentId(id);
        if (byStudentId != null) {
            Registration registration = registrationRepository.findByStudentIdNFLike(byStudentId.getRegistrationId());
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setAddress(byStudentId.getAddress());
            studentDTO.setFirstName(byStudentId.getFirstName());
            studentDTO.setGurdianContact(byStudentId.getGurdianContact());
            studentDTO.setLastName(byStudentId.getLastName());
            studentDTO.setNic(byStudentId.getNic());
            studentDTO.setBirthday(byStudentId.getBirthDay());
            studentDTO.setStatus(byStudentId.getStatus());
            studentDTO.setGurdianName(byStudentId.getGurdianName());
            studentDTO.setMiddleName(byStudentId.getMiddleName());
            studentDTO.setRegistrationId(registration.getRegistrationId());
            studentDTO.setSchoolName(byStudentId.getSchoolName());
            studentDTO.setRegDate(registration.getRegistrationDate());
            studentDTO.setEmail(byStudentId.getEmail());

            return  studentDTO;
        }else {
            return null;
        }
    }

    @Override
    public List<StudentDTO> searchStudent(StudentDTO dto) {
        if(dto != null) {
            List<Predicate> predicates = new ArrayList<>();
            List<StudentDTO> results = new ArrayList<>();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
            Root<Student> student = criteriaQuery.from(Student.class);

            if (dto.getStudentId() != 0) {
                Predicate studentIdPredicate =
                        criteriaBuilder.equal(student.get("studentId"), dto.getStudentId());

                predicates.add(studentIdPredicate);
            }

            if (dto.getFirstName() != null && (!dto.getFirstName().trim().isEmpty())) {
                Predicate firstNamePredicate =
                        criteriaBuilder.like(student.get("firstName"), dto.getFirstName());

                predicates.add(firstNamePredicate);
            }

            if (dto.getStatus() != null && (!dto.getStatus().trim().isEmpty())) {
                Predicate studentStatusPredicate =
                        criteriaBuilder.equal(student.get("status"), dto.getStatus());

                predicates.add(studentStatusPredicate);
            }

            if (dto.getNic() != null && (!dto.getNic().trim().isEmpty())) {
                Predicate studentNicPredicate =
                        criteriaBuilder.equal(student.get("nic"), dto.getNic());

                predicates.add(studentNicPredicate);
            }


            if (dto.getRegistrationId() != 0 && (!dto.getFirstName().trim().isEmpty())) {
                Predicate middleNamePredicate =
                        criteriaBuilder.equal(student.get("registration").get("registrationId"), dto.getRegistrationId());

                predicates.add(middleNamePredicate);
            }

            Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.where(finalPredicate);

            TypedQuery<Student> query = entityManager.createQuery(criteriaQuery);

            List<Student> resultCities = query.getResultList();

            resultCities.forEach(
                    (byStudentId) -> {
                        StudentDTO studentDTO = new StudentDTO();
                        Registration registration = registrationRepository.findByStudentIdNFLike(byStudentId.getRegistrationId());
                        studentDTO.setAddress(byStudentId.getAddress());
                        studentDTO.setFirstName(byStudentId.getFirstName());
                        studentDTO.setGurdianContact(byStudentId.getGurdianContact());
                        studentDTO.setLastName(byStudentId.getLastName());
                        studentDTO.setNic(byStudentId.getNic());
                        studentDTO.setBirthday(byStudentId.getBirthDay());
                        studentDTO.setStatus(byStudentId.getStatus());
                        studentDTO.setGurdianName(byStudentId.getGurdianName());
                        studentDTO.setMiddleName(byStudentId.getMiddleName());
                        studentDTO.setSchoolName(byStudentId.getSchoolName());
                        studentDTO.setRegistrationId(registration.getRegistrationId());
                        studentDTO.setEmail(byStudentId.getEmail());
                        studentDTO.setStudentId(byStudentId.getStudentId());
                        results.add(studentDTO);
                    }
            );
            return results;
        }else {
            return null;
        }
    }

    @Override
    public List<StudentDTO> getAll() throws Exception{
        List<StudentDTO>list = new ArrayList<>();
        List<Student> all = studentRepository.findAllByStatus("ACT");

        for (Student student:all) {
            StudentDTO studentDTO = new StudentDTO();
//            Registration registration = registrationRepository.findByStudentIdNFLike(student.getRegistrationId());
            studentDTO.setAddress(student.getAddress());
            studentDTO.setFirstName(student.getFirstName());
            studentDTO.setGurdianContact(student.getGurdianContact());
            studentDTO.setLastName(student.getLastName());
            studentDTO.setNic(student.getNic());
            studentDTO.setBirthday(student.getBirthDay());
            studentDTO.setStatus(student.getStatus());
            studentDTO.setGurdianName(student.getGurdianName());
            studentDTO.setMiddleName(student.getMiddleName());
//            studentDTO.setRegistrationId(registration.getRegistrationId());
            studentDTO.setSchoolName(student.getSchoolName());
            studentDTO.setStudentId(student.getStudentId());
            studentDTO.setEmail(student.getEmail());

            list.add(studentDTO);
        }
        return list;
    }

    @Override
    public List<Object> getAllEmptyStatusValues() {
        List<Student> status1 = studentRepository.findAllBy();
        List<Object> objects = new ArrayList<>();
        for (Student student : status1) {
            Student student1 = new Student();
            student1.setFirstName(student.getFirstName());
            student1.setNic(student.getNic());
            student1.setLastName(student.getLastName());
            student1.setMiddleName(student.getMiddleName());
            student1.setStudentId(student.getStudentId());
            student1.setGurdianName(student.getGurdianName());
            student1.setAddress(student.getAddress());
            student1.setStatus(student.getStatus());
            objects.add(student1);
        }


        return objects;
    }

    @Override
    public ArrayList<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        ArrayList<StudentDTO> alStudents = new ArrayList<>();

        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO(student.getStudentId(),
                    student.getFirstName(), student.getLastName(), student.getMiddleName(), student.getAddress(),
                    student.getSchoolName(), student.getGurdianName(), student.getNic(), student.getEmail(), student.getStatus(),
                    student.getGurdianContact(), Integer.parseInt(student.getRegistrationId()), student.getBirthDay(), student.getCreatedTime(), student.getLasrUpdateTime());

            alStudents.add(studentDTO);
        }

        return alStudents;
    }


}
