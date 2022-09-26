package com.example.orderdata.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderException extends RuntimeException{
    private String message;

    public OrderException(String message){
        this.message = message;
    }
}
