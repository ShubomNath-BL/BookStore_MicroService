package com.example.cartdata.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartException extends RuntimeException{
    private String message;

    public CartException(String message){
        this.message = message;
    }
}
