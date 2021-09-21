package com.esoft.student_management.repository.util.impl;

import com.esoft.student_management.entity.Orders;
import com.esoft.student_management.repository.util.OrdersJDBCRepository;
import com.esoft.student_management.util.GeneralResultsetExtracor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.esoft.student_management.util.QueryRepository.*;

@Repository
public class OrdersJDBCRepositoryImpl implements OrdersJDBCRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public  List<Map<String,Object>> getDailyOrders(String date) {
        Map<String,Object> query_param = new HashMap<>();
        query_param.put("date",date);
        List<Map<String,Object>> list = jdbcTemplate.query(GetDailyOrders,query_param,new GeneralResultsetExtracor(
               new String[]{"order_date","oid","qty","total_price","item_name"}
       ));
       return list;
    }

    @Override
    public List<Map<String, Object>> getDailyProfit(String date) {
        Map<String,Object> query_param = new HashMap<>();
        query_param.put("date",date);
        List<Map<String,Object>> list = jdbcTemplate.query(GetDailyProfit,query_param,new GeneralResultsetExtracor(
                new String[]{"order_date","total_price"}
        ));
        return list;
    }

    @Override
    public List<Map<String, Object>> getDailyOrderByStudent(String date) {
        Map<String,Object> query_param = new HashMap<>();
        query_param.put("date",date);
        List<Map<String,Object>> list = jdbcTemplate.query(GetStudentOfDailyBye,query_param,new GeneralResultsetExtracor(
                new String[]{"orderId","fullName","itemCode"}
        ));
        return list;
    }
}
