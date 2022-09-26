package com.example.orderdata.dto;

import com.example.orderdata.entity.OrderEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseDTO {
    private String message;
    private Object obj;
    public ResponseDTO(String message, OrderEntity orderEntity) {
        this.message = message;
        this.obj = orderEntity;
    }

    public ResponseDTO(String message, List<OrderEntity> orderEntities) {
        this.message = message;
        this.obj = orderEntities;
    }

    public ResponseDTO(String message, Optional<OrderEntity> response) {
        this.message = message;
        this.obj = response;
    }

    public ResponseDTO(String message, String s1) {
        this.message = message;
        this.obj = s1;
    }
}
