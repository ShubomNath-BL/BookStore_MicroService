package com.example.orderdata.service;

import com.example.orderdata.dto.OrderDTO;
import com.example.orderdata.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    OrderEntity save(OrderDTO orderDTO);

    List<OrderEntity> getAllData();

    Optional<OrderEntity> getById(int id);

    void deleteData(int id);

    OrderEntity updateOrder(OrderDTO orderDTO, int id);
}
