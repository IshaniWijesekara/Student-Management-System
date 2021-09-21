package com.esoft.student_management.service.impl;

import com.esoft.student_management.dto.PaymentDTO;
import com.esoft.student_management.entity.Classes;
import com.esoft.student_management.entity.Payment;
import com.esoft.student_management.entity.Student;
import com.esoft.student_management.repository.ClassesRepository;
import com.esoft.student_management.repository.PaymentRepository;
import com.esoft.student_management.repository.StudentRepository;
import com.esoft.student_management.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) throws Exception {
        LocalDate currentDate = LocalDate.now();
        if (paymentDTO != null) {
            Student student = studentRepository.findById(paymentDTO.getStudentId()).get();
            Classes classes = classesRepository.findById(paymentDTO.getClass_type()).get();
            Payment byStudentStudentIdAndMonth = paymentRepository.findByStudentStudentIdAndPaymentType(student.getStudentId(),paymentDTO.getType());
            if(byStudentStudentIdAndMonth == null) {
                Payment payment = new Payment();
                payment.setFee(paymentDTO.getFee());
                payment.setAmount(paymentDTO.getAmount());
                payment.setClass_type(classes);
                payment.setYear(currentDate.getYear());
                payment.setMonth(paymentDTO.getMonth());

                if (paymentDTO.getMonth().equals("January")) {
                    payment.setJanuary(paymentDTO.getMonth());
                }else if(paymentDTO.getMonth().equals("February")) {
                    payment.setFebruary(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("March")) {
                    payment.setMarch(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("April")) {
                    payment.setApril(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("May")) {
                   payment.setMay(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("June")) {
                    payment.setJuly(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("July")) {
                    payment.setJuly(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("August")) {
                    payment.setAugust(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("Sepetmber")) {
                    payment.setSeptember(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("October")) {
                    payment.setOctmber(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("November")) {
                    payment.setNovember(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("December")) {
                    payment.setDecember(paymentDTO.getMonth());
                }
                payment.setPaymentType(paymentDTO.getType());
                payment.setPaymentDate(paymentDTO.getPaymentDate());
                payment.setStudent(student);

                paymentRepository.save(payment);
            }else {
                byStudentStudentIdAndMonth.setPaymentId(byStudentStudentIdAndMonth.getPaymentId());
                byStudentStudentIdAndMonth.setFee(paymentDTO.getFee() + byStudentStudentIdAndMonth.getFee());
                byStudentStudentIdAndMonth.setYear(currentDate.getYear());
                byStudentStudentIdAndMonth.setMonth(paymentDTO.getMonth());
                byStudentStudentIdAndMonth.setAmount(paymentDTO.getAmount());
                byStudentStudentIdAndMonth.setClass_type(classes);

                if (paymentDTO.getMonth().equals("January")) {
                    byStudentStudentIdAndMonth.setJanuary(paymentDTO.getMonth());
                }else if(paymentDTO.getMonth().equals("February")) {
                    byStudentStudentIdAndMonth.setFebruary(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("March")) {
                    byStudentStudentIdAndMonth.setMarch(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("April")) {
                    byStudentStudentIdAndMonth.setApril(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("May")) {
                    byStudentStudentIdAndMonth.setMay(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("June")) {
                    byStudentStudentIdAndMonth.setJuly(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("July")) {
                    byStudentStudentIdAndMonth.setJuly(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("August")) {
                    byStudentStudentIdAndMonth.setAugust(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("Sepetmber")) {
                    byStudentStudentIdAndMonth.setSeptember(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("October")) {
                    byStudentStudentIdAndMonth.setOctmber(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("November")) {
                    byStudentStudentIdAndMonth.setNovember(paymentDTO.getMonth());
                }else if (paymentDTO.getMonth().equals("December")) {
                    byStudentStudentIdAndMonth.setDecember(paymentDTO.getMonth());
                }
                byStudentStudentIdAndMonth.setPaymentType(paymentDTO.getType());
                byStudentStudentIdAndMonth.setPaymentDate(paymentDTO.getPaymentDate());
                byStudentStudentIdAndMonth.setStudent(student);

                paymentRepository.save(byStudentStudentIdAndMonth);
            }

        }else {
            return false;
        }
        return true;
    }

    @Override
    public List<PaymentDTO> getAllPayments() throws Exception {
        List<Payment> all = paymentRepository.findAll();
        List<PaymentDTO>list = new ArrayList<>();

        for (Payment payment:all) {
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setPaymentId(payment.getPaymentId());
            paymentDTO.setFee(payment.getFee());
            paymentDTO.setAmount(payment.getAmount());
            if (paymentDTO.getMonth() == "January") {
                payment.setJanuary(paymentDTO.getMonth());
            }else if(paymentDTO.getMonth() =="February") {
                payment.setFebruary(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() =="March") {
                payment.setMarch(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "April") {
                payment.setApril(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "May") {
                payment.setMay(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "June") {
                payment.setJuly(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "July") {
                payment.setJuly(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "August") {
                payment.setAugust(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "Sepetmber") {
                payment.setSeptember(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "October") {
                payment.setOctmber(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "November") {
                payment.setNovember(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "December") {
                payment.setDecember(paymentDTO.getMonth());
            }
            paymentDTO.setMonth(payment.getMonth());
            paymentDTO.setType(payment.getPaymentType());
            paymentDTO.setPaymentDate(payment.getPaymentDate());
            paymentDTO.setStudentId(payment.getStudent().getStudentId());
            paymentDTO.setNic(payment.getStudent().getNic());
            paymentDTO.setYear(payment.getYear());
            paymentDTO.setName(payment.getStudent().getFirstName() + " " + payment.getStudent().getMiddleName() + " " + payment.getStudent().getLastName());

            list.add(paymentDTO);
        }
        return list;
    }

    @Override
    public List<PaymentDTO> findByPaymentByMonth(String month) throws Exception {
        List<PaymentDTO>list = new ArrayList<>();
       if(month != null) {
           List<Payment> allByMonth = null;
           if (month.equals("January")) {
              allByMonth = paymentRepository.findAllByJanuary(month);
           }else if(month.equals("February")) {
               allByMonth = paymentRepository.findAllByFebruary(month);
           }else if (month.equals("March")) {
               allByMonth = paymentRepository.findAllByMarch(month);
           }else if (month.equals("April")) {
               allByMonth = paymentRepository.findAllByApril(month);
           }else if (month.equals("May")) {
               allByMonth = paymentRepository.findAllByMay(month);
           }else if (month.equals("June")) {
               allByMonth = paymentRepository.findAllByJune(month);
           }else if (month.equals("July")) {
               allByMonth = paymentRepository.findAllByJuly(month);
           }else if (month.equals("August")) {
               allByMonth = paymentRepository.findAllByAugust(month);
           }else if (month.equals("Sepetmber")) {
               allByMonth = paymentRepository.findAllBySeptember(month);
           }else if (month.equals("October")) {
               allByMonth = paymentRepository.findAllByOctmber(month);
           }else if (month.equals("November")) {
              allByMonth = paymentRepository.findAllByNovember(month);
           }else if (month.equals("December")) {
               allByMonth = paymentRepository.findAllByDecember(month);
           }


           for (Payment payment:allByMonth) {
               PaymentDTO paymentDTO = new PaymentDTO();
               paymentDTO.setPaymentId(payment.getPaymentId());
               paymentDTO.setFee(payment.getFee());
               paymentDTO.setAmount(payment.getAmount());
               paymentDTO.setPaymentDate(payment.getPaymentDate());
               paymentDTO.setType(payment.getPaymentType());
               paymentDTO.setMonth(payment.getMonth());
               if (paymentDTO.getMonth() == "January") {
                   payment.setJanuary(paymentDTO.getMonth());
               }else if(paymentDTO.getMonth() =="February") {
                   payment.setFebruary(paymentDTO.getMonth());
               }else if (paymentDTO.getMonth() =="March") {
                   payment.setMarch(paymentDTO.getMonth());
               }else if (paymentDTO.getMonth() == "April") {
                   payment.setApril(paymentDTO.getMonth());
               }else if (paymentDTO.getMonth() == "May") {
                   payment.setMay(paymentDTO.getMonth());
               }else if (paymentDTO.getMonth() == "June") {
                   payment.setJuly(paymentDTO.getMonth());
               }else if (paymentDTO.getMonth() == "July") {
                   payment.setJuly(paymentDTO.getMonth());
               }else if (paymentDTO.getMonth() == "August") {
                   payment.setAugust(paymentDTO.getMonth());
               }else if (paymentDTO.getMonth() == "Sepetmber") {
                   payment.setSeptember(paymentDTO.getMonth());
               }else if (paymentDTO.getMonth() == "October") {
                   payment.setOctmber(paymentDTO.getMonth());
               }else if (paymentDTO.getMonth() == "November") {
                   payment.setNovember(paymentDTO.getMonth());
               }else if (paymentDTO.getMonth() == "December") {
                   payment.setDecember(paymentDTO.getMonth());
               }
               paymentDTO.setYear(payment.getYear());
               paymentDTO.setNic(payment.getStudent().getNic());
               paymentDTO.setStudentId(payment.getStudent().getStudentId());
               paymentDTO.setName(payment.getStudent().getFirstName() + " " + payment.getStudent().getMiddleName() + " " + payment.getStudent().getLastName());

               list.add(paymentDTO);
           }
       }else {
           return null;
       }
       return list;
    }

    @Override
    public List<PaymentDTO> findByPaymentByStudentId(Integer studentId) throws Exception {
        List<Payment> all = paymentRepository.findAllByStudentStudentId(studentId);
        List<PaymentDTO>list = new ArrayList<>();

        for (Payment payment:all) {
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setFee(payment.getFee());
            paymentDTO.setAmount(payment.getAmount());
            paymentDTO.setPaymentId(payment.getPaymentId());
            paymentDTO.setType(payment.getPaymentType());
            paymentDTO.setNic(payment.getStudent().getNic());
            paymentDTO.setPaymentDate(payment.getPaymentDate());
            paymentDTO.setMonth(payment.getMonth());
            if (paymentDTO.getMonth() == "January") {
                payment.setJanuary(paymentDTO.getMonth());
            }else if(paymentDTO.getMonth() =="February") {
                payment.setFebruary(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() =="March") {
                payment.setMarch(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "April") {
                payment.setApril(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "May") {
                payment.setMay(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "June") {
                payment.setJuly(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "July") {
                payment.setJuly(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "August") {
                payment.setAugust(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "Sepetmber") {
                payment.setSeptember(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "October") {
                payment.setOctmber(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "November") {
                payment.setNovember(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "December") {
                payment.setDecember(paymentDTO.getMonth());
            }
            paymentDTO.setYear(payment.getYear());
            paymentDTO.setStudentId(payment.getStudent().getStudentId());
            paymentDTO.setName(payment.getStudent().getFirstName() + " " + payment.getStudent().getMiddleName() + " " + payment.getStudent().getLastName());

            list.add(paymentDTO);
        }
        return list;
    }

    @Override
    public PaymentDTO findByPaymentById(Integer id) throws Exception {
        Payment payment = paymentRepository.findById(id).get();
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setFee(payment.getFee());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setType(payment.getPaymentType());
        paymentDTO.setYear(payment.getYear());
        paymentDTO.setNic(payment.getStudent().getNic());
        paymentDTO.setPaymentDate(payment.getPaymentDate());
        if (paymentDTO.getMonth() == "January") {
            payment.setJanuary(paymentDTO.getMonth());
        }else if(paymentDTO.getMonth() =="February") {
            payment.setFebruary(paymentDTO.getMonth());
        }else if (paymentDTO.getMonth() =="March") {
            payment.setMarch(paymentDTO.getMonth());
        }else if (paymentDTO.getMonth() == "April") {
            payment.setApril(paymentDTO.getMonth());
        }else if (paymentDTO.getMonth() == "May") {
            payment.setMay(paymentDTO.getMonth());
        }else if (paymentDTO.getMonth() == "June") {
            payment.setJuly(paymentDTO.getMonth());
        }else if (paymentDTO.getMonth() == "July") {
            payment.setJuly(paymentDTO.getMonth());
        }else if (paymentDTO.getMonth() == "August") {
            payment.setAugust(paymentDTO.getMonth());
        }else if (paymentDTO.getMonth() == "Sepetmber") {
            payment.setSeptember(paymentDTO.getMonth());
        }else if (paymentDTO.getMonth() == "October") {
            payment.setOctmber(paymentDTO.getMonth());
        }else if (paymentDTO.getMonth() == "November") {
            payment.setNovember(paymentDTO.getMonth());
        }else if (paymentDTO.getMonth() == "December") {
            payment.setDecember(paymentDTO.getMonth());
        }
        paymentDTO.setMonth(payment.getMonth());
        paymentDTO.setStudentId(payment.getStudent().getStudentId());
        paymentDTO.setName(payment.getStudent().getFirstName() + " " + payment.getStudent().getMiddleName() + " " + payment.getStudent().getLastName());
        return paymentDTO;
    }

    @Override
    public boolean updatePayment(PaymentDTO paymentDTO) throws Exception {
        if(paymentDTO != null) {
            Payment payment = paymentRepository.findById(paymentDTO.getPaymentId()).get();
            payment.setPaymentId(payment.getPaymentId());
            payment.setFee(paymentDTO.getFee());
            payment.setAmount(paymentDTO.getAmount());
            payment.setPaymentId(paymentDTO.getPaymentId());
            payment.setPaymentType(paymentDTO.getType());
            payment.setPaymentDate(paymentDTO.getPaymentDate());
            if (paymentDTO.getMonth() == "January") {
                payment.setJanuary(paymentDTO.getMonth());
            }else if(paymentDTO.getMonth() =="February") {
                payment.setFebruary(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() =="March") {
                payment.setMarch(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "April") {
                payment.setApril(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "May") {
                payment.setMay(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "June") {
                payment.setJuly(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "July") {
                payment.setJuly(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "August") {
                payment.setAugust(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "Sepetmber") {
                payment.setSeptember(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "October") {
                payment.setOctmber(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "November") {
                payment.setNovember(paymentDTO.getMonth());
            }else if (paymentDTO.getMonth() == "December") {
                payment.setDecember(paymentDTO.getMonth());
            }
            payment.setMonth(paymentDTO.getMonth());
            payment.setYear(payment.getYear());
            paymentRepository.save(payment);
            return true;
        }else {
            return false;
        }
    }
}
