package com.esoft.student_management.service;

import com.esoft.student_management.dto.PaymentDTO;

import java.util.List;

public interface PaymentService{
    public boolean savePayment(PaymentDTO paymentDTO)throws Exception;

    public List<PaymentDTO> getAllPayments()throws Exception;

    public List<PaymentDTO> findByPaymentByMonth(String month)throws Exception;

    public List<PaymentDTO> findByPaymentByStudentId(Integer studentId)throws Exception;

    public PaymentDTO findByPaymentById(Integer id)throws Exception;

    public boolean updatePayment(PaymentDTO paymentDTO)throws Exception;
}
