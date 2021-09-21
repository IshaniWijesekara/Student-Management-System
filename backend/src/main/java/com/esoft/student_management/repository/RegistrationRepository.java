package com.esoft.student_management.repository;

import com.esoft.student_management.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RegistrationRepository extends JpaRepository<Registration,Integer> {


    Registration findByStudentIdNFLike(String id);
   Registration findByStudentNic(String nic);
}
