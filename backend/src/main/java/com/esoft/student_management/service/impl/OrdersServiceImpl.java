package com.esoft.student_management.service.impl;


import com.esoft.student_management.dto.*;
import com.esoft.student_management.entity.*;
import com.esoft.student_management.repository.OrdersRepository;
import com.esoft.student_management.repository.util.OrdersJDBCRepository;
import com.esoft.student_management.service.OrdersService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.transaction.Transactional;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private OrdersJDBCRepository ordersJDBCRepository;

    @Override
    public boolean saveOrder(OrdersDTO orderDTO) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
         cal.getTime();

        System.out.println("............"+cal.getTime());

        List<OrderDetailsDTO> orderDetailDTOs = orderDTO.getOrderDetailDTOs();
        Student student = new Student(orderDTO.getStudentDTO().getStudentId(),
                orderDTO.getStudentDTO().getFirstName(),
                orderDTO.getStudentDTO().getLastName(),
                orderDTO.getStudentDTO().getMiddleName(),
                orderDTO.getStudentDTO().getAddress(),
                orderDTO.getStudentDTO().getNic(),
                orderDTO.getStudentDTO().getSchoolName(),
                orderDTO.getStudentDTO().getGurdianName(),
                orderDTO.getStudentDTO().getGurdianContact(),
                orderDTO.getStudentDTO().getEmail(),
                orderDTO.getStudentDTO().getStatus(),
                orderDTO.getStudentDTO().getBirthDay(),
                orderDTO.getStudentDTO().getClasses(),
                orderDTO.getStudentDTO().getSectionName(),
                orderDTO.getStudentDTO().getLecContact(),
                orderDTO.getStudentDTO().getCreatedTime(),
                orderDTO.getStudentDTO().getLasrUpdateTime()
        );

        Item item = null;
        List<OrderDetails> orderDetails = new ArrayList<>();

        Orders order = new Orders(orderDTO.getOid(),cal.getTime(), orderDetails, student);

        for (OrderDetailsDTO orderDetailDTO : orderDetailDTOs) {
            item = new Item();
            ItemDTO itemDTO = orderDetailDTO.getItemDTO();
            item.setItemCode(itemDTO.getItemCode());
            item.setQty(itemDTO.getQty());
            item.setItemName(itemDTO.getDescription());
            item.setUnitPrice(itemDTO.getUnitPrice());

            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setItem(item);
            orderDetail.setOrders(order);
            orderDetail.setOrderDate(cal.getTime());
            orderDetail.setItem(item);
            orderDetail.setQty(orderDetailDTO.getQty());
            orderDetail.setTotalPrice(orderDetailDTO.getTotalPrice());
            orderDetail.setOrderDetail_PK(new OrderDetail_PK(orderDTO.getOid(), itemDTO.getItemCode()));

            orderDetails.add(orderDetail);
        }

        orderRepository.save(order);

        return true;
    }

    @Override
    public long getTotalOrders() {
        return orderRepository.getTotalOrders();
    }

    @Override
    public ByteArrayOutputStream getDailyOrders(String date) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        List<DailyOrderDTO> all = new ArrayList<>();
        List<Map<String, Object>> list = ordersJDBCRepository.getDailyOrders(date);
        for (int i = 0; i < list.size(); i++) {
            DailyOrderDTO dailyOrderDTO = new DailyOrderDTO();
            String orderDate = String.valueOf(list.get(i).get("order_date"));
            String orderId = String.valueOf(list.get(i).get("oid"));
            String qty = String.valueOf(list.get(i).get("qty"));
            String price = String.valueOf(list.get(i).get("total_price"));
            String itemName = String.valueOf(list.get(i).get("item_name"));

            dailyOrderDTO.setDate(orderDate);
            dailyOrderDTO.setItemName(itemName);
            dailyOrderDTO.setOid(orderId);
            dailyOrderDTO.setQty(qty);
            dailyOrderDTO.setTotalPrice(price);
            all.add(dailyOrderDTO);
        }

        String reportPath = "E:\\Reports";

        // Add parameters
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("date", date);

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\daily.jrxml");
            JRDataSource dataSource = new JRBeanCollectionDataSource(all);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\daily.pdf");
            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasperPrint));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            exp.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }


        return output;


    }

    @Override
    public ByteArrayOutputStream getDailyProfit(String date) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        List<Map<String, Object>> list = ordersJDBCRepository.getDailyProfit(date);
        DailyOrderDTO dailyOrderDTO = new DailyOrderDTO();
        String orderDate = String.valueOf(list.get(0).get("order_date"));
        String price = String.valueOf(list.get(0).get("total_price"));
        dailyOrderDTO.setDate(orderDate);
        dailyOrderDTO.setTotalPrice(price);
        String reportPath = "E:\\Reports";
        List<DailyOrderDTO> dailyOrderDTOS = new ArrayList<>();
        dailyOrderDTOS.add(dailyOrderDTO);
        // Add parameters
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("date", date);
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\profitDaily.jrxml");
            JRDataSource dataSource = new JRBeanCollectionDataSource(dailyOrderDTOS);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\profitDaily.pdf");
            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasperPrint));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            exp.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }

        return output;

    }

    @Override
    public ByteArrayOutputStream getDailyOrderByStudent(String date) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        List<StudentDailyOrderDTO> all = new ArrayList<>();

        List<Map<String, Object>> list = ordersJDBCRepository.getDailyOrderByStudent(date);
        for (int i = 0; i < list.size(); i++) {
            StudentDailyOrderDTO dailyOrderDTO = new StudentDailyOrderDTO();
            String orderId = String.valueOf(list.get(i).get("orderId"));
            String fullName = String.valueOf(list.get(i).get("fullName"));
            String itemCode = String.valueOf(list.get(i).get("itemCode"));

           // orderId
            dailyOrderDTO.setFullName(fullName);
            dailyOrderDTO.setItemCode(itemCode);
            dailyOrderDTO.setOrderId(orderId);

            all.add(dailyOrderDTO);
        }



        String reportPath = "E:\\Reports";

        // Add parameters
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("date", date);

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\orderStudent.jrxml");
            JRDataSource dataSource = new JRBeanCollectionDataSource(all);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\orderStudent.pdf");
            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasperPrint));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            exp.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }


        return output;
    }
}
