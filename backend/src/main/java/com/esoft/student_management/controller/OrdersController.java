package com.esoft.student_management.controller;


import com.esoft.student_management.dto.DailyOrderDTO;
import com.esoft.student_management.dto.OrdersDTO;
import com.esoft.student_management.service.OrdersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;


@RestController
@RequestMapping("${app.endpoint.api}/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping(value = "/saveOrders",produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveCustomer(@RequestBody OrdersDTO ordersDTO) {
        return ordersService.saveOrder(ordersDTO);
    }

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getOrdersCount() {
        return ordersService.getTotalOrders();
    }

    @GetMapping(value = "/getDailyOrders/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> getDailyOrders(@PathVariable("date")String date) {

        ByteArrayOutputStream output = ordersService.getDailyOrders(date);
        InputStream stream = new ByteArrayInputStream(output.toByteArray());
        InputStreamResource resource = new InputStreamResource(stream);

        return ResponseEntity.ok()
                .contentLength(output.size())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }

    @GetMapping(value = "/getDailyProfit/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> getDailyProfit(@PathVariable("date")String date) {

        ByteArrayOutputStream output = ordersService.getDailyProfit(date);
        InputStream stream = new ByteArrayInputStream(output.toByteArray());
        InputStreamResource resource = new InputStreamResource(stream);


        return ResponseEntity.ok()
                .contentLength(output.size())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }


    @GetMapping(value = "/getDailyOrdersStudents/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> getDailyStudentsOrders(@PathVariable("date")String date) {

        ByteArrayOutputStream output = ordersService.getDailyOrderByStudent(date);
        InputStream stream = new ByteArrayInputStream(output.toByteArray());
        InputStreamResource resource = new InputStreamResource(stream);


        return ResponseEntity.ok()
                .contentLength(output.size())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }


}
