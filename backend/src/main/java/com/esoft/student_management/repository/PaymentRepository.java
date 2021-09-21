package com.esoft.student_management.repository;

import com.esoft.student_management.entity.Classes;
import com.esoft.student_management.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    List<Payment> findAllByMonth(String month);

    List<Payment> findAllByOctmber(String month);

    List<Payment> findAllByJanuary(String month);

    List<Payment> findAllByFebruary(String month);

    List<Payment> findAllByMarch(String month);

    List<Payment> findAllByApril(String month);

    List<Payment> findAllByMay(String month);

    List<Payment> findAllByJune(String month);

    List<Payment> findAllByJuly(String month);

    List<Payment> findAllByAugust(String month);

    List<Payment> findAllBySeptember(String month);

    List<Payment> findAllByNovember(String month);

    List<Payment> findAllByDecember(String month);

    List<Payment> findAllByStudentStudentId(Integer id);

    Payment findByStudentStudentIdAndPaymentType(Integer id, String type);

    Payment findByStudentStudentIdAndMonthAndPaymentType(Integer id, String name,String type);
}
