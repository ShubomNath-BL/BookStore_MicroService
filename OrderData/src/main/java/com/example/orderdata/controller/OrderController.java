package com.example.orderdata.controller;

import com.example.orderdata.dto.OrderDTO;
import com.example.orderdata.dto.ResponseDTO;
import com.example.orderdata.entity.OrderEntity;
import com.example.orderdata.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @PostMapping("add")
    public ResponseEntity<ResponseDTO> addOrder(@RequestBody OrderDTO orderDTO){
        OrderEntity orderEntity = orderService.save(orderDTO);
        ResponseDTO responseOrderDTO = new ResponseDTO("Order has been submitted", orderEntity);
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.OK);
    }
    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getAllOrders(){
        List<OrderEntity> orderEntities = orderService.getAllData();
        ResponseDTO responseOrderDTO = new ResponseDTO("All the orders are:- ", orderEntities);
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.OK);
    }
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<ResponseDTO> getOrderById(@PathVariable int id){
        Optional<OrderEntity> response = orderService.getById(id);
        ResponseDTO responseOrderDTO = new ResponseDTO("Orders related to ID are:- ", response);
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteOrderById(@PathVariable int id){
        orderService.deleteData(id);
        ResponseDTO responseOrderDTO = new ResponseDTO("Order details has been deleted: ", "Deleted id: "+id);
        return new ResponseEntity<>(responseOrderDTO,HttpStatus.GONE);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateOrderById(@RequestBody OrderDTO orderDTO, @PathVariable int id){
        OrderEntity response = orderService.updateOrder(orderDTO, id);
        ResponseDTO responseOrderDTO = new ResponseDTO("Orders after updated are:- ", response);
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.OK);
    }
}
