package com.esoft.student_management.repository.util;


import java.util.List;
import java.util.Map;

public interface OrdersJDBCRepository{

     List<Map<String,Object>> getDailyOrders(String date);

     List<Map<String,Object>> getDailyProfit(String date);

     List<Map<String,Object>> getDailyOrderByStudent(String date);

}
