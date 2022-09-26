package com.example.cartdata.dto;

import com.example.cartdata.entity.CartEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseDTO {
    private String message;
    private Object obj;
    public ResponseDTO(String message, CartEntity response) {
        this.message = message;
        this.obj = response;
    }

    public ResponseDTO(String message, List<CartEntity> response) {
        this.message = message;
        this.obj = response;
    }

    public ResponseDTO(String message, Optional<CartEntity> response) {
        this.message = message;
        this.obj = response;
    }

    public ResponseDTO(String message, String s1) {
        this.message = message;
        this.obj = s1;
    }
}
