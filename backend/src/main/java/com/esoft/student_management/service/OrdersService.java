package com.esoft.student_management.service;

import com.esoft.student_management.dto.DailyOrderDTO;
import com.esoft.student_management.dto.OrdersDTO;
import com.esoft.student_management.entity.Orders;
import org.springframework.data.repository.query.Param;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface OrdersService {

    public boolean saveOrder(OrdersDTO orderDTO);

    public long getTotalOrders();

    public ByteArrayOutputStream getDailyOrders(String date);

    public ByteArrayOutputStream getDailyProfit(String date);

    public ByteArrayOutputStream getDailyOrderByStudent(String date);

}
