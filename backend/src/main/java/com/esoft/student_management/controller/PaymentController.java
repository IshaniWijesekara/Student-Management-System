package com.esoft.student_management.controller;

import com.esoft.student_management.config.SystemVarList;
import com.esoft.student_management.dto.PaymentDTO;
import com.esoft.student_management.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${app.endpoint.api}/payment")
public class PaymentController {
    final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/savePayment")
    public boolean save(@RequestBody PaymentDTO paymentDTO) {
        try {
            return paymentService.savePayment(paymentDTO);
        } catch (Exception e) {
            logger.error("Error when save payment data  : " +e);
            return false;
        }
    }

    @PostMapping("/updatePayment")
    public boolean update(@RequestBody PaymentDTO paymentDTO) {
        try {
            return paymentService.updatePayment(paymentDTO);
        } catch (Exception e) {
            logger.error("Error when update payment data  : " +e);
            return false;
        }
    }

    @GetMapping("/getPayment/{month}")
    public ResponseEntity<PaymentDTO> getPaymentByMonth(  @PathVariable("month") String month ){
        try {
            List<PaymentDTO> payment = paymentService.findByPaymentByMonth(month);
            return new ResponseEntity(payment, HttpStatus.CREATED);
        } catch (Exception e){
            logger.error("Error when getting payment data by month : " +e);
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPaymentById/{id}")
    public ResponseEntity<PaymentDTO> getPaymentByStudentId(  @PathVariable("id") Integer id ){
        try {
            List<PaymentDTO> byStudentId = paymentService.findByPaymentByStudentId(id);
            return new ResponseEntity(byStudentId, HttpStatus.CREATED);
        } catch (Exception e){
            logger.error("Error when getting payment data by student : " +e);
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPaymentDetails/{id}")
    public PaymentDTO getPaymentById(@PathVariable("id") Integer id ){
        try {
            PaymentDTO byPaymentById = paymentService.findByPaymentById(id);
            return byPaymentById;
        } catch (Exception e){
            logger.error("Error when getting payment data by student : " +e);
            return null;
        }
    }

    @GetMapping("/")
    public List<PaymentDTO> getAllPayments() {
        try {
            return paymentService.getAllPayments();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in getting Payments: ", e);
            return null;
        }
    }
}
