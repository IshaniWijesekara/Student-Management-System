package com.esoft.student_management.repository.util.impl;

import com.esoft.student_management.util.GeneralResultsetExtracor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.esoft.student_management.util.QueryRepository.*;

@Repository
public class AttendanceJDBCReppository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> getAttendanceByMonth(String classType,String fromDate,String toDate) {
        Map<String,Object> query_param = new HashMap<>();
        query_param.put("class_type",classType);
        query_param.put("fromDate",fromDate);
        query_param.put("toDate",toDate);
        List<Map<String,Object>> list = jdbcTemplate.query(GetAttendnaceByStudentAndClass,query_param,new GeneralResultsetExtracor(
                new String[]{"student_id","first_name","nic","date","in_time","month"}
        ));
        return list;
    }

    public List<Map<String,Object>> getAttendanceByStudent(String classType,String studentId,String fromDate,String toDate) {
        Map<String,Object> query_param = new HashMap<>();
        query_param.put("class_type",classType);
        query_param.put("student_id",studentId);
        query_param.put("fromDate",fromDate);
        query_param.put("toDate",toDate);
        List<Map<String,Object>> list = jdbcTemplate.query(GetAttendanceByStudentAndDate,query_param,new GeneralResultsetExtracor(
                new String[]{"student_id","first_name","nic","date","in_time","month"}
        ));
        return list;
    }

    public List<Map<String,Object>> getLateAttendanceByStudent(String classType,String inTime,String fromDate,String toDate) {
        Map<String,Object> query_param = new HashMap<>();
        query_param.put("class_type",classType);
        query_param.put("in_time",inTime);
        query_param.put("fromDate",fromDate);
        query_param.put("toDate",toDate);
        List<Map<String,Object>> list = jdbcTemplate.query(GetLateAttendanceByClass,query_param,new GeneralResultsetExtracor(
                new String[]{"student_id","first_name","nic","date","in_time","month"}
        ));
        return list;
    }

    public List<Map<String,Object>> getEmployeeAttendance(String fromDate,String toDate) {
        Map<String,Object> query_param = new HashMap<>();
        query_param.put("fromDate",fromDate);
        query_param.put("toDate",toDate);
        List<Map<String,Object>> list = jdbcTemplate.query(GetEmployeeAttendance,query_param,new GeneralResultsetExtracor(
                new String[]{"id","name","email","address","date","in_time","out_time"}
        ));
        return list;
    }

    public List<Map<String,Object>> getPaymentDetails(String classType) {
        Map<String,Object> query_param = new HashMap<>();
        query_param.put("class_type",classType);
        List<Map<String,Object>> list = jdbcTemplate.query(GetPaymentDetails,query_param,new GeneralResultsetExtracor(
                new String[]{"first_name","email","nic","fee","payment_date","PAYMENT_TYPE"}
        ));
        return list;
    }

    public List<Map<String,Object>> getPaymentProfit(String classType) {
        Map<String,Object> query_param = new HashMap<>();
        query_param.put("class_type",classType);
        List<Map<String,Object>> list = jdbcTemplate.query(GetPaymentProfit,query_param,new GeneralResultsetExtracor(
                new String[]{"Totfee"}
        ));
        return list;
    }
}
